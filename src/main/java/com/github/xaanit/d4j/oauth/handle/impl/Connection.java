package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.handle.IConnection;

/**
 * Created by Jacob on 4/29/2017.
 */
public class Connection implements IConnection {

	private OAuthUser user;
	private String id;
	private String name;
	private String type;
	private boolean revoked;

	public Connection(OAuthUser user, String id, String name, String type, boolean revoked) {
		this.user = user;
		this.id = id;
		this.name = name;
		this.type = type;
		this.revoked = revoked;
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
	public OAuthUser getUser() {
		return this.user;
	}
}
