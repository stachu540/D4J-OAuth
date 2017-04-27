package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import org.apache.http.message.BasicNameValuePair;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.internal.DiscordEndpoints;
import sx.blah.discord.api.internal.Requests;
import sx.blah.discord.api.internal.json.objects.UserObject;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.cache.LongMap;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacob on 4/21/2017.
 */
public  class OAuthUser implements IOAuthUser {

	private final IUser user;
	private final String accessToken;

	public OAuthUser(IUser user, String accessToken) {
		this.user = user;
		this.accessToken = accessToken;
	}

	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public Status getStatus() {
		return user.getStatus();
	}

	@Override
	public String getAvatar() {
		return user.getAvatar();
	}

	@Override
	public String getAvatarURL() {
		return user.getAvatarURL();
	}

	@Override
	public IPresence getPresence() {
		return user.getPresence();
	}

	@Override
	public String getDisplayName(IGuild guild) {
		return user.getDisplayName(guild);
	}

	@Override
	public String mention() {
		return user.mention();
	}

	@Override
	public String mention(boolean mentionWithNickname) {
		return user.mention(mentionWithNickname);
	}

	@Override
	public String getDiscriminator() {
		return user.getDiscriminator();
	}

	@Override
	public List<IRole> getRolesForGuild(IGuild guild) {
		return user.getRolesForGuild(guild);
	}

	@Override
	public EnumSet<Permissions> getPermissionsForGuild(IGuild guild) {
		return user.getPermissionsForGuild(guild);
	}

	@Override
	public String getNicknameForGuild(IGuild guild) {
		return user.getNicknameForGuild(guild);
	}

	@Override
	public IVoiceState getVoiceStateForGuild(IGuild guild) {
		return user.getVoiceStateForGuild(guild);
	}

	@Override
	public Map<String, IVoiceState> getVoiceStates() {
		return user.getVoiceStates();
	}

	@Override
	public LongMap<IVoiceState> getVoiceStatesLong() {
		return user.getVoiceStatesLong();
	}

	@Override
	public boolean isBot() {
		return user.isBot();
	}

	@Override
	public void moveToVoiceChannel(IVoiceChannel newChannel)
			throws DiscordException, RateLimitException, MissingPermissionsException {
		user.moveToVoiceChannel(newChannel);
	}


	@Override
	public IPrivateChannel getOrCreatePMChannel() throws RateLimitException, DiscordException {
		return user.getOrCreatePMChannel();
	}

	@Override
	public void addRole(IRole role)
			throws MissingPermissionsException, RateLimitException, DiscordException {
		user.addRole(role);
	}

	@Override
	public void removeRole(IRole role)
			throws MissingPermissionsException, RateLimitException, DiscordException {
		user.removeRole(role);
	}

	@Override
	public String getID() {
		return user.getID();
	}

	@Override
	public long getLongID() {
		return user.getLongID();
	}

	@Override
	public String getStringID() {
		return user.getStringID();
	}

	@Override
	public IDiscordClient getClient() {
		return user.getClient();
	}

	@Override
	public IShard getShard() {
		return user.getShard();
	}

	@Override
	public LocalDateTime getCreationDate() {
		return user.getCreationDate();
	}

	@Override
	public IUser copy() {
		return new OAuthUser(user.copy(), accessToken);
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public List<String> getConnections() {
		return null;
	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public boolean is2FAEnabled() {
		UserObject o = Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "/users/@me", UserObject.class, new BasicNameValuePair("Authorization", "Bearer " + this.accessToken));
		return false;
	}

	@Override
	public List<IGuild> getGuilds() {
		return null;
	}

	@Override
	public boolean joinGuild(IGuild guild) {
		return false;
	}

	@Override
	public boolean makeGroupPM(IDiscordOAuth[] users) {
		return false;
	}
}
