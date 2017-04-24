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

	IOAuthUser getOAuthUser(IUser user);

	IOAuthUser getOAuthUserForID(long id);

	/**
	 * Gets the {@link IDiscordClient} client instance.
	 *
	 * @return The client
	 */
	IDiscordClient getClient();
}
