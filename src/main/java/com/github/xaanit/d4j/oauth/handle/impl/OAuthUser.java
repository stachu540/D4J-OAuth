package com.github.xaanit.d4j.oauth.handle.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.xaanit.d4j.oauth.Scope;
import com.github.xaanit.d4j.oauth.handle.IConnection;
import com.github.xaanit.d4j.oauth.handle.IDiscordOAuth;
import com.github.xaanit.d4j.oauth.handle.IOAuthUser;
import com.github.xaanit.d4j.oauth.handle.IUserGuild;
import com.github.xaanit.d4j.oauth.internal.json.objects.*;
import com.github.xaanit.d4j.oauth.util.MissingScopeException;
import org.apache.http.message.BasicNameValuePair;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.internal.DiscordClientImpl;
import sx.blah.discord.api.internal.DiscordEndpoints;
import sx.blah.discord.api.internal.DiscordUtils;
import sx.blah.discord.api.internal.Requests;
import sx.blah.discord.api.internal.json.objects.InviteObject;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.LogMarkers;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.cache.LongMap;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jacob on 4/21/2017.
 */
public class OAuthUser implements IOAuthUser {
	private final IDiscordOAuth oauth;
	private final IUser user;
	private String accessToken;
	private long expiresAt;
	private final String refreshToken;
	private final EnumSet<Scope> scopes;

	public OAuthUser(IDiscordOAuth oauth, IUser user, String accessToken, String refreshToken, long expiresAt, EnumSet<Scope> scopes) {
		this.oauth = oauth;
		this.user = user;
		this.accessToken = accessToken;
		this.expiresAt = expiresAt;
		this.refreshToken = refreshToken;
		this.scopes = scopes;
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
		return new OAuthUser(oauth, user.copy(), accessToken, refreshToken, expiresAt, scopes);
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public String getRefreshToken() {
		return refreshToken;
	}

	@Override
	public IDiscordOAuth getOAuth() {
		return oauth;
	}

	@Override
	public void refreshToken() {
		AuthorizeUserResponse response = Requests.GENERAL_REQUESTS.POST.makeRequest(DiscordEndpoints.OAUTH + "token", String.format("grant_type=refresh_token&refresh_token=%s&client_id=%s&client_secret=%s", refreshToken, oauth.getClientID(), oauth.getClientSecret()), AuthorizeUserResponse.class, new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
		this.accessToken = response.access_token;
		this.expiresAt = response.expires_at;
	}

	@Override
	public List<IConnection> getConnections() {
		checkScope(Scope.CONNECTIONS);
		refreshTokenIfNeeded();

		return Arrays.stream(Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me/connections", UserConnectionObject[].class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()))).map(c -> new Connection(this, c.id, c.name, c.type, c.revoked, c.visibility == 1, c.friend_sync)).collect(Collectors.toList());
	}

	@Override
	public String getEmail() {
		checkScope(Scope.EMAIL);
		refreshTokenIfNeeded();

		OAuthUserObject o = Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me", OAuthUserObject.class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()));
		return o.email;
	}

	@Override
	public boolean isVerified() {
		checkScope(Scope.EMAIL);
		refreshTokenIfNeeded();

		OAuthUserObject o = Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me", OAuthUserObject.class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()));
		return o.verified;
	}

	@Override
	public boolean is2FAEnabled() {
		//No scope check here, since any call seems to allow for an identify
		refreshTokenIfNeeded();

		OAuthUserObject o = Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me", OAuthUserObject.class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()));
		return o.mfa_enabled;
	}

	@Override
	public List<IUserGuild> getGuilds() {
		checkScope(Scope.GUILDS);
		refreshTokenIfNeeded();

		UserGuildObject[] userGuilds = Requests.GENERAL_REQUESTS.GET.makeRequest(DiscordEndpoints.USERS + "@me/guilds", UserGuildObject[].class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()));
		return Arrays.stream(userGuilds).map(g -> new UserGuild(g.id, g.name, g.icon, g.owner, g.permissions, this)).collect(Collectors.toList());
	}

	@Override
	public void joinGuild(IGuild guild) {
		checkScope(Scope.GUILDS_JOIN);
		refreshTokenIfNeeded();
		DiscordUtils.checkPermissions(this.user.getClient(), guild, EnumSet.of(Permissions.CREATE_INVITE));

		try {
			((DiscordClientImpl) this.user.getClient()).REQUESTS.PATCH.makeRequest(
					DiscordEndpoints.GUILDS + guild.getLongID() + "/members/" + user.getStringID(),
					DiscordUtils.MAPPER_NO_NULLS.writeValueAsString(new MemberAddRequest.Builder(getAccessToken()).build()));
		} catch (JsonProcessingException e) {
			Discord4J.LOGGER.error(LogMarkers.HANDLE, "Discord4J Internal Exception", e);
		}
	}

	public void joinGuild(IGuild guild, String nickname) {
		checkScope(Scope.GUILDS_JOIN);
		refreshTokenIfNeeded();
		DiscordUtils.checkPermissions(this.user.getClient(), guild, EnumSet.of(Permissions.CREATE_INVITE, Permissions.MANAGE_NICKNAMES));

		try {
			((DiscordClientImpl) this.user.getClient()).REQUESTS.PATCH.makeRequest(
					DiscordEndpoints.GUILDS + guild.getLongID() + "/members/" + user.getStringID(),
					DiscordUtils.MAPPER_NO_NULLS.writeValueAsString(new MemberAddRequest.Builder(getAccessToken()).nick(nickname).build()));
		} catch (JsonProcessingException e) {
			Discord4J.LOGGER.error(LogMarkers.HANDLE, "Discord4J Internal Exception", e);
		}
	}

	public void joinGuild(IGuild guild, String nickname, IRole[] roles) {
		checkScope(Scope.GUILDS_JOIN);
		refreshTokenIfNeeded();
		DiscordUtils.checkPermissions(this.user.getClient(), guild, EnumSet.of(Permissions.CREATE_INVITE, Permissions.MANAGE_NICKNAMES, Permissions.MANAGE_ROLES));

		try {
			((DiscordClientImpl) this.user.getClient()).REQUESTS.PUT.makeRequest(
					DiscordEndpoints.GUILDS + guild.getLongID() + "/members/" + user.getStringID(),
					DiscordUtils.MAPPER_NO_NULLS.writeValueAsString(new MemberAddRequest.Builder(getAccessToken()).nick(nickname).roles(roles).build()));
		} catch (JsonProcessingException e) {
			Discord4J.LOGGER.error(LogMarkers.HANDLE, "Discord4J Internal Exception", e);
		}
	}

	public void joinGuild(IGuild guild, String nickname, IRole[] roles, boolean muted) {
		checkScope(Scope.GUILDS_JOIN);
		refreshTokenIfNeeded();
		DiscordUtils.checkPermissions(this.user.getClient(), guild, EnumSet.of(Permissions.CREATE_INVITE, Permissions.MANAGE_NICKNAMES, Permissions.MANAGE_ROLES, Permissions.VOICE_MUTE_MEMBERS));

		try {
			((DiscordClientImpl) this.user.getClient()).REQUESTS.PATCH.makeRequest(
					DiscordEndpoints.GUILDS + guild.getLongID() + "/members/" + user.getStringID(),
					DiscordUtils.MAPPER_NO_NULLS.writeValueAsString(new MemberAddRequest.Builder(getAccessToken()).nick(nickname).roles(roles).mute(muted).build()));
		} catch (JsonProcessingException e) {
			Discord4J.LOGGER.error(LogMarkers.HANDLE, "Discord4J Internal Exception", e);
		}
	}

	public void joinGuild(IGuild guild, String nickname, IRole[] roles, boolean muted, boolean deafened) {
		checkScope(Scope.GUILDS_JOIN);
		refreshTokenIfNeeded();
		DiscordUtils.checkPermissions(this.user.getClient(), guild, EnumSet.of(Permissions.CREATE_INVITE, Permissions.MANAGE_NICKNAMES, Permissions.MANAGE_ROLES, Permissions.VOICE_MUTE_MEMBERS, Permissions.VOICE_DEAFEN_MEMBERS));

		try {
			((DiscordClientImpl) this.user.getClient()).REQUESTS.PATCH.makeRequest(
					DiscordEndpoints.GUILDS + guild.getLongID() + "/members/" + user.getStringID(),
					DiscordUtils.MAPPER_NO_NULLS.writeValueAsString(new MemberAddRequest.Builder(getAccessToken()).nick(nickname).roles(roles).mute(muted).deafen(deafened).build()));
		} catch (JsonProcessingException e) {
			Discord4J.LOGGER.error(LogMarkers.HANDLE, "Discord4J Internal Exception", e);
		}
	}

	@Override
	public IInvite joinGuild(IInvite invite) {
		return joinGuildWithInviteCode(invite.getCode());
	}

	@Override
	public IInvite joinGuildWithInviteCode(String inviteCode) {
		checkScope(Scope.GUILDS_JOIN);
		refreshTokenIfNeeded();

		InviteObject obj = Requests.GENERAL_REQUESTS.POST.makeRequest(DiscordEndpoints.INVITE + inviteCode, InviteObject.class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()));
		return DiscordUtils.getInviteFromJSON(user.getClient(), obj);
	}

	public void makeGroupPM(IOAuthUser[] users) {
//		String[] authTokens = new String[users.length];
//		String[] nicks = new String[users.length];
//		for (int i = 0; i < users.length; i++) {
//			authTokens[i] = users[i].getAccessToken();
//			nicks[i] = users[i].getStringID() + ":" + users[i].getName();
//		}
//		Requests.GENERAL_REQUESTS.POST.makeRequest(DiscordEndpoints.USERS + "@me/channels", OAuthUserObject.class, new BasicNameValuePair("Authorization", "Bearer " + this.getAccessToken()));
	}

	@Override
	public EnumSet<Scope> getAuthorizedScopes() {
		return scopes;
	}

	private void checkScope(Scope scope) {
		if (!scopes.contains(scope))
			throw new MissingScopeException(scope);
	}

	private void refreshTokenIfNeeded() {
		if(System.currentTimeMillis() > expiresAt)
			refreshToken();
	}

	public void updateToken(String accessToken, long expiresAt) {
		this.accessToken = accessToken;
		this.expiresAt = expiresAt;
	}
}
