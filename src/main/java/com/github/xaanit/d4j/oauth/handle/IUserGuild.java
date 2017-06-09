package com.github.xaanit.d4j.oauth.handle;

import sx.blah.discord.handle.obj.Permissions;

import java.util.EnumSet;

public interface IUserGuild {

	/**
	 * Gets the long ID for the guild
	 *
	 * @return The long ID
	 */
	long getLongID();

	/**
	 * Gets the String ID for the guild
	 *
	 * @return The String ID
	 */
	String getStringID();

	/**
	 * Gets the name of the guild
	 *
	 * @return The name
	 */
	String getName();

	/**
	 * Gets the Icon URL for the guild
	 *
	 * @return The icon URL
	 */
	String getIconURL();

	/**
	 * Gets if the user is the owner of the guild
	 *
	 * @return {@code true} if they are; otherwise {@code false}
	 */
	boolean isOwner();

	/**
	 * Gets the permissions for the user has for this guild
	 *
	 * @return The set of permissions
	 */
	EnumSet<Permissions> getPermissions();

	/**
	 * Gets the user
	 *
	 * @return The user
	 */
	IOAuthUser getUser();
}
