package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.Scope;
import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import java.net.URL;
import sx.blah.discord.handle.obj.IUser;


public class DiscordOAuth implements IDiscordOAuth {

	private Scope[] scopes;
	private String clientID;
	private URL redirectUrl;


	public DiscordOAuth() {
		this.scopes = null;
		this.clientID = "";
		this.redirectUrl = null;
	}

	public String buildAuthUrl() {
		return null;
	}

	public void setScopes(Scope[] scopes) {
		this.scopes = scopes;
	}

	public void setClientID(String id) {
		this.clientID = id;
	}

	public void setRedirectUrl(URL url) {
		this.redirectUrl = url;
	}

	public String getAccessTokenForUser(IUser user) {
		return null;
	}
}
