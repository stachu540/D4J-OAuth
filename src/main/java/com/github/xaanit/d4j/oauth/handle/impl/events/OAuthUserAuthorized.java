package com.github.xaanit.d4j.oauth.handle.impl.events;

import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import sx.blah.discord.handle.impl.events.user.UserEvent;

public class OAuthUserAuthorized extends UserEvent {
	private final IOAuthUser user;

	public OAuthUserAuthorized(IOAuthUser user) {
		super(user);
		this.user = user;
	}

	@Override
	public IOAuthUser getUser() {
		return user;
	}
}
