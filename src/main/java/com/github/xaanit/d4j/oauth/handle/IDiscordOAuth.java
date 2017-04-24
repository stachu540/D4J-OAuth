package com.github.xaanit.d4j.oauth.handle;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IUser;

public interface IDiscordOAuth {

	/**
	 * Builds the AuthURL to send to the user.
	 *
	 * @return The AuthURl
	 */
	String buildAuthUrl();

	/**
	 * Gets the access token for a user..
	 *
	 * @param user The user to get the access token from
	 * @return The access token
	 */
	String getAccessTokenForUser(IUser user);

	/**
	 * Gets the {@link IDiscordClient} client instance.
	 *
	 * @return The client
	 */
	IDiscordClient getClient();
}
