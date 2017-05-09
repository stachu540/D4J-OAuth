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

	/**
	 * Attempts to join a guild as the user, and set their nickname
	 * Note: This action is only performable if the app has a bundled bot, and the bot is in that guild.
	 * The bot also requires the CREATE_INSTANT_INVITE permission, MANAGE_NICKNAMES, and MANAGE_ROLES
	 *
	 * @param guild    The guild to try and join
	 * @param nickname The nickname to set to this user upon joining
	 * @param roles    The roles the user should have on join
	 */
	void joinGuild(IGuild guild, String nickname, IRole[] roles);

	/**
	 * Attempts to join a guild as the user, and set their nickname
	 * Note: This action is only performable if the app has a bundled bot, and the bot is in that guild.
	 * The bot also requires the CREATE_INSTANT_INVITE permission, MANAGE_NICKNAMES, MANAGE_ROLES, and MUTE_MEMBERS
	 *
	 * @param guild    The guild to try and join
	 * @param nickname The nickname to set to this user upon joining
	 * @param roles    The roles the user should have on join
	 * @param muted    If the user should be muted on join
	 */
	void joinGuild(IGuild guild, String nickname, IRole[] roles, boolean muted);

	/**
	 * Attempts to join a guild as the user, and set their nickname
	 * Note: This action is only performable if the app has a bundled bot, and the bot is in that guild.
	 * The bot also requires the CREATE_INSTANT_INVITE permission, MANAGE_NICKNAMES, MANAGE_ROLES, MUTE_MEMBERS, and DEAFEN_MEMBERS
	 *
	 * @param guild    The guild to try and join
	 * @param nickname The nickname to set to this user upon joining
	 * @param roles    The roles the user should have on join
	 * @param muted    If the user should be muted on join
	 * @param deafened If the user should be deafened on join
	 */
	void joinGuild(IGuild guild, String nickname, IRole[] roles, boolean muted, boolean deafened);

	/**
	 * Attempts to join a guild as the user.
	 *
	 * @param invite The {@link IInvite} invite
	 * @return The invite object
	 */
	IInvite joinGuild(IInvite invite);

	/**
	 * Attempts to join a guild as the user.
	 *
	 * @param inviteCode The string invite code
	 * @return The invite link
	 */
	IInvite joinGuildWithInviteCode(String inviteCode);

	/**
	 * Gets the scopes authorised for this user
	 *
	 * @return The enum set of scopes
	 */
	EnumSet<Scope> getAuthorizedScopes();
}
