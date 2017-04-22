package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.Scope;
import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import sx.blah.discord.handle.obj.IUser;


public class DiscordOAuth implements IDiscordOAuth {

	private Scope[] scopes = null;
	private String clientID = "";
	private String redirectUrl = "";


	public DiscordOAuth() {
	}

	public String buildAuthUrl() {
		if(scopes == null) {
			throw new IllegalArgumentException("You must have at least one scope!");
		}
		if(clientID.isEmpty()) {
			throw new IllegalArgumentException("You need to provide a client ID!");
		}
		if(redirectUrl.isEmpty()) {
			throw new IllegalArgumentException("You must provide a redirect URL!");
		}
		return null;
	}

	public void setScopes(Scope[] scopes) {
		this.scopes = scopes;
	}

	public void setClientID(String id) {
		this.clientID = id;
	}

	public void setRedirectUrl(String url) {
		this.redirectUrl = url;
	}

	public String getAccessTokenForUser(IUser user) {
		return null;
	}
}
