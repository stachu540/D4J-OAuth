package com.github.xaanit.d4j.oauth.internal.json.objects;


/**
 * Represents a json oauth user connection object
 */
public class UserConnectionObject {

	public String id;

	public String name;

	public String type;

	public boolean revoked = false;

	public int visibility;

	public boolean friend_sync;
}
