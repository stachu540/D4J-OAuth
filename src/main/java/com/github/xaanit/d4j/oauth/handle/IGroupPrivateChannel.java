package com.github.xaanit.d4j.oauth.handle;

/**
 * Created by Jacob on 4/29/2017.
 */
public interface IGroupPrivateChannel {

	void addMember();

	void removeMember();

	IOAuthUser[] getUsers();

}
