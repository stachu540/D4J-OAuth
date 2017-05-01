package com.github.xaanit.d4j.oauth.handle;

import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

public interface IDiscordOAuth {

	/**
	 * Builds the AuthURL to send to the user.
	 *
	 * @return The AuthURl
	 */
	String buildAuthUrl();

	IOAuthUser getOAuthUser(IUser user);

	IOAuthUser getOAuthUserForID(long id);

	List<IOAuthWebhook> getWebhooks();

	IOAuthWebhook getWebhookByID(long id);

	/**
	 * Gets the {@link IDiscordClient} client instance.
	 *
	 * @return The client
	 */
	IDiscordClient getClient();

	HttpServer getHttpServer();

	Router getRouter();
}
