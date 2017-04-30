package com.github.xaanit.d4j.oauth.handle;

import sx.blah.discord.handle.obj.IChannel;

/** Turn away all ye who enter here
 *  Unimplemented for now, since we have no idea how this works - PMs only sometimes appear on mobile
 */
public interface IGroupPrivateChannel extends IChannel {

	void addRecipient(IOAuthUser user);

	void removeReceipient(IOAuthUser user);

	IOAuthUser[] getRecipients();
}
