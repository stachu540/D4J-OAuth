package com.github.xaanit.d4j.oauth.util;

import com.github.xaanit.d4j.oauth.Scope;
import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import com.github.xaanit.d4j.oauth.handle.impl.DiscordOAuth;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.RoutingContext;
import sx.blah.discord.api.IDiscordClient;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by undermybrella on 22/4/17.
 */
public class DiscordOAuthBuilder {
	private Scope[] scopes = null;
	private String clientID = null;
	private String clientSecret = null;
	private String redirectUrl = null;
	private String redirectPath = null;
	private Consumer<RoutingContext> onFail = context -> context.response().end("Fail!");
	private BiConsumer<RoutingContext, IOAuthUser> onSuccess = (context, user) -> context.response().end("Hello " + user.getName());
	private HttpServerOptions serverOptions = new HttpServerOptions();
	private final IDiscordClient client;

	public DiscordOAuthBuilder(IDiscordClient client) {
		this.client = client;
	}

	/**
	 * Sets the scope of the OAuth client.
	 *
	 * @param scopes The Scope enum array
	 */
	public DiscordOAuthBuilder withScopes(Scope... scopes) {
		this.scopes = scopes;
		return this;
	}


	/**
	 * Sets the Client ID for the AuthURL.
	 *
	 * @param clientID The Client ID to with it to
	 */
	public DiscordOAuthBuilder withClientID(String clientID) {
		this.clientID = clientID;
		return this;
	}

	public DiscordOAuthBuilder withClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

	public DiscordOAuthBuilder withFailureHandler(Consumer<RoutingContext> onFail) {
		this.onFail = onFail;
		return this;
	}

	public DiscordOAuthBuilder withSuccessHandler(BiConsumer<RoutingContext, IOAuthUser> onSuccess) {
		this.onSuccess = onSuccess;
		return this;
	}

	/**
	 * Sets the redirect URL for the AuthURL.
	 *
	 * @param url The URL to with it to
	 */
	public DiscordOAuthBuilder withRedirectUrl(String url) {
		this.redirectUrl = url;
		return this;
	}

	public DiscordOAuthBuilder withHttpServerOptions(HttpServerOptions options) {
		this.serverOptions = options;
		return this;
	}

	public IDiscordOAuth build() {
		if (scopes == null)
			throw new IllegalArgumentException("No scopes provided!");
		if (clientID == null)
			throw new IllegalArgumentException("No client ID provided");
		if (clientSecret == null)
			throw new IllegalArgumentException("No client secret provided!");
		if (redirectUrl == null)
			throw new IllegalArgumentException("No redirect URL provided!");
		if (redirectPath == null) {
			if (redirectUrl.lastIndexOf('/') == -1)
				throw new IllegalArgumentException("No redirect path provided!");
			redirectPath = redirectUrl.substring(redirectUrl.lastIndexOf('/'));
		}

		return new DiscordOAuth(client, scopes, clientID, clientSecret, redirectUrl, redirectPath, serverOptions, onFail, onSuccess);
	}

}
