package com.github.xaanit.d4j.oauth.handle;

import com.github.xaanit.d4j.oauth.handle.impl.OAuthUser;

/**
 * Created by Jacob on 4/26/2017.
 */
public interface IConnection {
	/**
	 * Gets the ID of the connection
	 *
	 * @return The ID
	 */
	String getID();

	/**
	 * Gets the name of the connection
	 *
	 * @return The name
	 */
	String getName();

	/**
	 * Ges the Type of the connection
	 *
	 * @return The type's name
	 */
	String getType();

	/**
	 * Gets if the connection is revoked or not
	 *
	 * @return {@code true} if it is; otherwise {@code false}
	 */
	boolean isRevoked();

	/**
	 * Gets the associated {@link OAuthUser}
	 *
	 * @return The user
	 */
	OAuthUser getUser();

}
