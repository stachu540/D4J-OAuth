package com.github.xaanit.d4j.oauth.handle;

import com.github.xaanit.d4j.oauth.Scope;
import io.vertx.ext.auth.oauth2.AccessToken;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IInvite;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

import java.util.EnumSet;
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
	 * Gets the full token for this user. Unlikely to be useful, unless you plan on saving tokens across instances.
	 *
	 * @return The full token for this user.
	 */
	AccessToken getToken();

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
	 * Attempts to join a guild as the user.
	 * Note: This action is only performable if the app has a bundled bot, and the bot is in that guild.
	 * The bot also requires the CREATE_INSTANT_INVITE permission
	 *
	 * @param guild The guild to try and join
	 */
	void joinGuild(IGuild guild);

	/**
	 * Attempts to join a guild as the user, and set their nickname
	 * Note: This action is only performable if the app has a bundled bot, and the bot is in that guild.
	 * The bot also requires the CREATE_INSTANT_INVITE permission and MANAGE_NICKNAMES
	 *
	 * @param guild    The guild to try and join
	 * @param nickname The nickname to set to this user upon joining
	 */
	void joinGuild(IGuild guild, String nickname);

	void joinGuild(IGuild guild, String nickname, IRole[] roles);

	void joinGuild(IGuild guild, String nickname, IRole[] roles, boolean muted);

	void joinGuild(IGuild guild, String nickname, IRole[] roles, boolean muted, boolean deafened);

	IInvite joinGuild(IInvite invite);

	IInvite joinGuildWithInviteCode(String inviteCode);

	EnumSet<Scope> getAuthorizedScopes();
}
