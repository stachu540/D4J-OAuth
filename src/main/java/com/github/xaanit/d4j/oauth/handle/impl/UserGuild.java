package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import com.github.xaanit.d4j.oauth.handle.IUserGuild;
import sx.blah.discord.handle.obj.Permissions;

import java.util.EnumSet;

/**
 * Created by Jacob on 4/27/2017.
 */
public class UserGuild implements IUserGuild {

	private String id;
	private String name;
	private String icon;
	private boolean owner;
	private int permissions;
	private IOAuthUser user;

	public UserGuild(String id, String name, String icon, boolean owner, int permissions, IOAuthUser user) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.owner = owner;
		this.permissions = permissions;
		this.user = user;
	}

	@Override
	public long getLongID() {
		return Long.parseLong(this.getStringID());
	}

	@Override
	public String getStringID() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getIconURL() {
		return this.icon;
	}

	@Override
	public Boolean isOwner() {
		return this.owner;
	}

	@Override
	public EnumSet<Permissions> getPermissions() {
		if (this.owner)
			return EnumSet.allOf(Permissions.class);
		return Permissions.getAllowedPermissionsForNumber(this.permissions);
	}

	@Override
	public IOAuthUser getUser() {
		return this.user;
	}
}
