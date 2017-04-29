package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.handle.IConnection;

/**
 * Created by Jacob on 4/29/2017.
 */
public class Connection implements IConnection {
	private final OAuthUser user;
	private final String id;
	private final String name;
	private final String type;
	private final boolean revoked;
	private final boolean visible;
	private final boolean friendSync;

	public Connection(OAuthUser user, String id, String name, String type, boolean revoked, boolean visible, boolean friendSync) {
		this.user = user;
		this.id = id;
		this.name = name;
		this.type = type;
		this.revoked = revoked;
		this.visible = visible;
		this.friendSync = friendSync;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean isRevoked() {
		return this.revoked;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public boolean hasFriendSyncEnabled() {
		return this.friendSync;
	}

	@Override
	public OAuthUser getUser() {
		return this.user;
	}
}
