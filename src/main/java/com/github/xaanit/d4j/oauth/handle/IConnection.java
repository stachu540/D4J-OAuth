package com.github.xaanit.d4j.oauth.handle;

/**
 * Created by Jacob on 4/26/2017.
 */
public interface IConnection {

	String getID();
	String getName();
	String getType();
	boolean isRevoked();
	String[] getIntegrations();

}
