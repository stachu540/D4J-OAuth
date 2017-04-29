package com.github.xaanit.d4j.oauth.handle;

import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/**
 * Created by Jacob on 4/21/2017.
 */
public interface IOAuthUser extends IUser {
	/**
	 * Get the access token for this user
	 *
	 * @return the access token
	 */
	String getAccessToken();

	/**
	 * Gets the list of {@link IConnection} connections the user has.
	 *
	 * @return The list of connections
	 */
	List<IConnection> getConnections();

	/**
	 * Gets the email of the user.
	 *
	 * @return The email
	 */
	String getEmail();

	/**
	 * Returns if the user has verified their email
	 *
	 * @return {@code true} if they have; otherwise {@code false}
	 */
	boolean isVerified();

	/**
	 * Returns if the user has Two factor authentication on
	 *
	 * @return {@code true} if they do; otherwise {@code false}
	 */
	boolean is2FAEnabled();

	/**
	 * Gets the list of {@link IUserGuild} objects that the user is connected to.
	 *
	 * @return The list of guilds
	 */
	List<IUserGuild> getGuilds();

	/**
	 * Attempts to leave a guild as the user.
	 *
	 * @param guild The guild to try and leave
	 * @return {@code true} if left; otherwise {@code false}
	 */
	void leaveGuild(IUserGuild guild);

	/**
	 * Attempts to join a guild as the user.
	 *
	 * @param guild The guild to try and join
	 * @return {@code true} if joined; otherwise {@code false}
	 */
	void joinGuild(IUserGuild guild);

	/**
	 * Makes a group PM of the {@link IOAuthUser} users. NOTE: There is a Discord bug. Any group DMs made by this are invisible on desktop.
	 *
	 * @param users The users.
	 * @return {@code true} if successful; otherwise {@code false}
	 */
	void makeGroupPM(IOAuthUser[] users);
}
