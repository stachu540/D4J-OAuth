package com.github.xaanit.d4j.oauth.internal.json.objects;

import sx.blah.discord.api.internal.json.objects.GuildObject;

/**
 * Represents a json OAuth user guild
 */
public class UserGuildObject extends GuildObject {

	/**
	 * Gets the long ID of the guild.
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.GUILDS}
	 */
	public long getLongID;

	/**
	 * Gets the String ID of the guild.
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.GUILDS}
	 */
	public String getStringID;

	/**
	 * Gets the name of the guild.
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.GUILDS}
	 */
	public String getName;

	/**
	 * Gets the Icon URL of the guild.
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.GUILDS}
	 */
	public String getIconURL;

	/**
	 * Returns if the user is the owner of the guild.
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.GUILDS}
	 */
	public Boolean isOwner;

	/**
	 * Gets the bitwise integer of the users enabled/disabled permissions
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.GUILDS}
	 */
	public int getPermissions;
}
