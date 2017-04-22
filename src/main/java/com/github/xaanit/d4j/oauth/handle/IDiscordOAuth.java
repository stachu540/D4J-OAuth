package com.github.xaanit.d4j.oauth.handle;

import com.github.xaanit.d4j.oauth.Scope;
import sx.blah.discord.handle.obj.IUser;

public interface IDiscordOAuth {

	/**
	 * Builds the AuthURL to send to the user.
	 *
	 * @return The AuthURl
	 */
	String buildAuthUrl();

	/**
	 * Sets the scope of the OAuth client.
	 *
	 * @param scopes The Scope enum array
	 */
	void setScopes(Scope[] scopes);

	/**
	 * Sets the Client ID for the AuthURL.
	 *
	 * @param id The ID to set it to
	 */
	void setClientID(String id);

	/**
	 * Sets the redirect URL for the AuthURL.
	 *
	 * @param url The URL to set it to
	 */
	void setRedirectUrl(String url);

	/**
	 * Gets the access token for a user..
	 *
	 * @param user The user to get the access token from
	 * @return The access token
	 */
	String getAccessTokenForUser(IUser user);

}
