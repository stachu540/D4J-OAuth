package com.github.xaanit.d4j.oauth.handle;

import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Jacob on 4/30/2017.
 */
public interface IOAuthWebhook {

	/**
	 * Gets the URL of the webhook
	 *
	 * @return The URL
	 */
	String getURL();

	/**
	 * Gets the ID of the webhook
	 *
	 * @return The ID
	 */
	String getID();

	/**
	 * Gets the long ID of the webhook's guild
	 *
	 * @return The ID as a long
	 */
	long getGuildLongID();

	/**
	 * Gets the String ID of the webhook's guild
	 *
	 * @return The ID as a string
	 */
	String getGuildStringID();

	/**
	 * Gets the {@link sx.blah.discord.handle.obj.IUser} object that posts the
	 *
	 * @return The user
	 */
	IUser getUser();

	/**
	 * Gets the name of the webhook
	 *
	 * @return The name
	 */
	String getName();

	/**
	 * Gets the Avatar URL of the webhook
	 *
	 * @return The avatar's URL
	 */
	String getAvatarURL();

	/**
	 * Gets the token of the webhook
	 *
	 * @return The token
	 */
	String getToken();
}
