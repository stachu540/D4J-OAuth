package com.github.xaanit.d4j.oauth.handle;

import sx.blah.discord.handle.obj.IUser;

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
}
