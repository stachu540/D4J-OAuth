package com.github.xaanit.d4j.oauth.internal.json.objects;

/**
 * Represents a json OAuth user guild
 */
public class UserGuildObject {

	/**
	 * The ID of the guild.
	 */
	public String id;

	/**
	 * Name of the guild.
	 */
	public String name;

	/**
	 * Icon hash of the guild.
	 */
	public String icon;

	/**
	 * If the user is the owner of the guild.
	 */
	public boolean owner;

	/**
	 * Bitwise integer of the users enabled/disabled permissions.
	 */
	public int permissions;
}
