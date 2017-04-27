package com.github.xaanit.d4j.oauth.internal.json.objects;

import sx.blah.discord.api.internal.json.objects.UserObject;

/**
 * Represents a json oauth user object
 */
public class OAuthUserObject extends UserObject {
	/**
	 * If the user has 2 Factor Authentication enabled
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.IDENTIFY}
	 */
	public Boolean mfa_enabled;

	/**
	 * If the user has verified their email
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.EMAIL}
	 */
	public Boolean verified;

	/**
	 * The user's email
	 * Required scope: {@link com.github.xaanit.d4j.oauth.Scope Scope.EMAIL}
	 */
	public String email;
}
