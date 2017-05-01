package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import com.github.xaanit.d4j.oauth.handle.IOAuthWebhook;
import com.github.xaanit.d4j.oauth.internal.json.objects.OAuthWebhookObject;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Jacob on 4/30/2017.
 */
public class OAuthWebhook implements IOAuthWebhook {

	private String url;
	private String id;
	private String guildID;
	private IUser user;
	private String name;
	private String avatarURL;
	private String token;

	public OAuthWebhook(String url, String id, String guildID, IUser user, String name, String avatarURL, String token) {
		this.url = url;
		this.id = id;
		this.guildID = guildID;
		this.user = user;
		this.name = name;
		this.avatarURL = avatarURL;
		this.token = token;
	}

	public OAuthWebhook(OAuthWebhookObject webhookObject, IOAuthUser user) {
		this(webhookObject.url, webhookObject.id, webhookObject.guild_id, user, webhookObject.name, webhookObject.avatar, webhookObject.token);
	}

	@Override
	public String getURL() {
		return this.url;
	}

	@Override
	public long getGuildLongID() {
		return Long.parseUnsignedLong(getGuildStringID());
	}

	@Override
	public String getGuildStringID() {
		return this.id;
	}

	@Override
	public IUser getUser() {
		return this.user;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getAvatarURL() {
		return this.avatarURL;
	}

	@Override
	public String getToken() {
		return this.token;
	}

	@Override
	public long getLongID() {
		return Long.parseUnsignedLong(this.id);
	}

	@Override
	public String getStringID() {
		return this.id;
	}
}
