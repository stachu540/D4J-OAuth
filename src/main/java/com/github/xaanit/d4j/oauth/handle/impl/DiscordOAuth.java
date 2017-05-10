package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.Scope;
import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import com.github.xaanit.d4j.oauth.handle.IOAuthWebhook;
import com.github.xaanit.d4j.oauth.handle.impl.events.OAuthUserAuthorized;
import com.github.xaanit.d4j.oauth.handle.impl.events.OAuthWebhookCreate;
import com.github.xaanit.d4j.oauth.internal.json.objects.AuthorizeUserResponse;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.http.message.BasicNameValuePair;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.DiscordClientImpl;
import sx.blah.discord.api.internal.DiscordEndpoints;
import sx.blah.discord.api.internal.DiscordUtils;
import sx.blah.discord.api.internal.Requests;
import sx.blah.discord.api.internal.json.objects.UserObject;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.cache.Cache;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DiscordOAuth implements IDiscordOAuth {
	private final Scope[] scopes;
	private final String redirectUrl;
	private final IDiscordClient client;
	private final OAuth2Auth oauth2Auth;
	private final HttpServer server;
	private final Router router;
	private final Cache<IOAuthUser> oauthUserCache;
	private final Cache<IOAuthWebhook> webhooks;

	public DiscordOAuth(IDiscordClient client, Scope[] scopes, String clientID, String clientSecret,
						String redirectUrl, String redirectPath, HttpServerOptions options, Consumer<RoutingContext> onFail, BiConsumer<RoutingContext, IOAuthUser> onSuccess) {
		this.scopes = scopes;
		this.client = client;
		this.redirectUrl = redirectUrl;
		this.oauthUserCache = new Cache<>((DiscordClientImpl) client, IOAuthUser.class);
		this.webhooks = new Cache<>((DiscordClientImpl) client, IOAuthWebhook.class);

		Vertx vertx = Vertx.vertx();
		server = vertx.createHttpServer(options);
		router = Router.router(vertx);

		oauth2Auth = OAuth2Auth.create(vertx, OAuth2FlowType.AUTH_CODE, new OAuth2ClientOptions()
				.setClientID(clientID)
				.setClientSecret(clientSecret)
				.setSite(DiscordEndpoints.APIBASE)
				.setTokenPath("/oauth2/token")
				.setRevocationPath("/oauth2/token/revoke")
				.setAuthorizationPath("/oauth2/authorize")
		);

		router.get(redirectPath).handler(context -> {
			MultiMap params = context.request().params();
			if (params.contains("error")) {
				Discord4J.LOGGER.error("Error! " + params.get("error"));
				onFail.accept(context);
			} else if (params.contains("code")) {
				oauth2Auth.getToken(new JsonObject().put("code", params.get("code")).put("redirect_uri", redirectUrl), res -> {
					if (res.failed()) {
						Discord4J.LOGGER.error("Result failed!");
						res.cause().printStackTrace();
						onFail.accept(context);
					} else {
						AuthorizeUserResponse authorize = res.result().principal().mapTo(AuthorizeUserResponse.class);
						Discord4J.LOGGER.debug("OAuth token received");

						if (authorize.webhook != null) {
							RequestBuffer.request(() -> {
								IUser user = DiscordUtils.getUserFromJSON(client.getShards().get(0), Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me", UserObject.class, new BasicNameValuePair("Authorization", "Bearer " + authorize.access_token)));
								IOAuthUser oauth = new OAuthUser(user, res.result(), Scope.getScopes(authorize.scope));
								oauthUserCache.put(oauth);

								IOAuthWebhook webhook = new OAuthWebhook(authorize.webhook, oauth);
								webhooks.put(webhook);

								onSuccess.accept(context, oauth);
								client.getDispatcher().dispatch(new OAuthUserAuthorized(oauth));
								client.getDispatcher().dispatch(new OAuthWebhookCreate(webhook));
							});
						} else {
							RequestBuffer.request(() -> {
								IUser user = DiscordUtils.getUserFromJSON(client.getShards().get(0), Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me", UserObject.class, new BasicNameValuePair("Authorization", "Bearer " + authorize.access_token)));
								IOAuthUser oauth = new OAuthUser(user, res.result(), Scope.getScopes(authorize.scope));
								oauthUserCache.put(oauth);

								onSuccess.accept(context, oauth);
								client.getDispatcher().dispatch(new OAuthUserAuthorized(oauth));
							});
						}
					}
				});
			}
		}); //The user did a thing!

		server.requestHandler(router::accept);
		server.listen();
	}

	public String buildAuthUrl() {
		return buildAuthUrl(scopes);
	}

	public String buildAuthUrl(Scope[] scopes) {
		try {
			return oauth2Auth.authorizeURL(new JsonObject()
					.put("redirect_uri", redirectUrl)
					.put("scopes", new JsonArray(Arrays.stream(scopes).map(Scope::getName).collect(Collectors.toList()))));
		} catch (Throwable th) {
			th.printStackTrace();
		}
		return "";
	}

	@Override
	public IOAuthUser getOAuthUser(IUser user) {
		return !oauthUserCache.containsKey(user.getLongID()) ? null : oauthUserCache.get(user.getLongID());
	}

	@Override
	public IOAuthUser getOAuthUserForID(long id) {
		return !oauthUserCache.containsKey(id) ? null : oauthUserCache.get(id);
	}

	@Override
	public List<IOAuthWebhook> getWebhooks() {
		return new LinkedList<>(webhooks.values());
	}

	@Override
	public IOAuthWebhook getWebhookByID(long id) {
		return !webhooks.containsKey(id) ? null : webhooks.get(id);
	}

	@Override
	public IDiscordClient getClient() {
		return client;
	}

	@Override
	public HttpServer getHttpServer() {
		return server;
	}

	@Override
	public Router getRouter() {
		return router;
	}
}
