package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.Scope;
import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import com.github.xaanit.d4j.oauth.handle.impl.events.OAuthUserAuthorized;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.Router;
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
import java.util.stream.Collectors;

public class DiscordOAuth implements IDiscordOAuth {

	private final String[] scopes;
	private final String redirectUrl;
	private final IDiscordClient client;
	private final OAuth2Auth oauth2Auth;
	private final HttpServer server;
	private final Router router;
	private final Cache<IOAuthUser> oauthUserCache;


	public DiscordOAuth(IDiscordClient client, Scope[] scopes, String clientID, String clientSecret,
						String redirectUrl, String redirectPath, HttpServerOptions options) {
		this.scopes = Arrays.stream(scopes).map(Scope::getName).collect(Collectors.toList()).toArray(new String[0]);
		this.client = client;
		this.redirectUrl = redirectUrl;
		this.oauthUserCache = new Cache<>((DiscordClientImpl) client, IOAuthUser.class);

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
			if (params.contains("error"))
				Discord4J.LOGGER.error("Error! " + params.get("error"));
			else if (params.contains("code")) {
				oauth2Auth.getToken(new JsonObject().put("code", params.get("code")).put("redirect_uri", redirectUrl), res -> {
					if (res.failed()) {
						Discord4J.LOGGER.error("Result failed!");
						res.cause().printStackTrace();
						context.response().end("Something went wrong!");
					} else {
						String accessToken = res.result().principal().getString("access_token");
						Discord4J.LOGGER.debug("OAuth token! " + accessToken);
						RequestBuffer.request(() -> {
							IUser user = DiscordUtils.getUserFromJSON(client.getShards().get(0), Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me", UserObject.class, new BasicNameValuePair("Authorization", "Bearer " + accessToken)));
							IOAuthUser oauth = new OAuthUser(user, accessToken);
							oauthUserCache.put(oauth);

							context.response().end("Something went right! Hello " + oauth.getName());
							client.getDispatcher().dispatch(new OAuthUserAuthorized(oauth));
						});
					}
				});
			}
		}); //The user did a thing!

		server.requestHandler(router::accept);
		server.listen();
	}

	public String buildAuthUrl() {
		return oauth2Auth.authorizeURL(new JsonObject()
				.put("redirect_uri", redirectUrl)
				.put("scope", String.join("+", scopes)));
	}

	@Override
	public IOAuthUser getOAuthUser(IUser user) {
		return oauthUserCache.get(user.getLongID());
	}

	@Override
	public IOAuthUser getOAuthUserForID(long id) {
		return oauthUserCache.get(id);
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
