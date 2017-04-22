package com.github.xaanit.d4j.oauth.handle.impl;

import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jacob on 4/21/2017.
 */
public class OAuthUser implements IOAuthUser {
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
	public Presences getPresence() {
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
	public Optional<String> getNicknameForGuild(IGuild guild) {
		return user.getNicknameForGuild(guild);
	}

	@Override
	public boolean isBot() {
		return user.isBot();
	}

	@Override
	public void moveToVoiceChannel(IVoiceChannel newChannel) throws DiscordException, RateLimitException, MissingPermissionsException {
		user.moveToVoiceChannel(newChannel);
	}

	@Override
	public List<IVoiceChannel> getConnectedVoiceChannels() {
		return user.getConnectedVoiceChannels();
	}

	@Override
	public IPrivateChannel getOrCreatePMChannel() throws RateLimitException, DiscordException {
		return user.getOrCreatePMChannel();
	}

	@Override
	public boolean isDeaf(IGuild guild) {
		return user.isDeaf(guild);
	}

	@Override
	public boolean isMuted(IGuild guild) {
		return user.isMuted(guild);
	}

	@Override
	public boolean isDeafLocally() {
		return user.isDeafLocally();
	}

	@Override
	public boolean isMutedLocally() {
		return user.isMutedLocally();
	}

	@Override
	public void addRole(IRole role) throws MissingPermissionsException, RateLimitException, DiscordException {
		user.addRole(role);
	}

	@Override
	public void removeRole(IRole role) throws MissingPermissionsException, RateLimitException, DiscordException {
		user.removeRole(role);
	}

	@Override
	public String getID() {
		return user.getID();
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
	public IUser copy() {
		return new OAuthUser(user.copy(), accessToken);
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}
}
