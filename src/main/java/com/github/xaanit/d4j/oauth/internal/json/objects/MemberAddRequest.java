package com.github.xaanit.d4j.oauth.internal.json.objects;

import sx.blah.discord.handle.obj.IRole;

import java.util.Arrays;

public class MemberAddRequest {

	public static class Builder {
		private IRole[] roles;
		private String nick;
		private Boolean mute;
		private Boolean deafen;
		private final String access;

		public Builder(String access) {
			this.access = access;
		}

		/**
		 * Sets the roles for the user to have.
		 *
		 * @param roles an array of Role objects.
		 * @return this builder, for chaining.
		 */
		public Builder roles(IRole[] roles) {
			this.roles = roles;
			return this;
		}

		/**
		 * Sets the user's nickname.
		 *
		 * @param nick the new user nickname.
		 * @return this builder, for chaining.
		 */
		public Builder nick(String nick) {
			this.nick = nick;
			return this;
		}

		/**
		 * Sets whether to mute the user.
		 *
		 * @param mute if the user should be muted.
		 * @return this builder, for chaining.
		 */
		public Builder mute(boolean mute) {
			this.mute = mute;
			return this;
		}

		/**
		 * Sets whether to deafen the user.
		 *
		 * @param deafen if the user should be deafened.
		 * @return this builder, for chaining.
		 */
		public Builder deafen(boolean deafen) {
			this.deafen = deafen;
			return this;
		}

		/**
		 * Builds the request object.
		 *
		 * @return the member edit request.
		 */
		public MemberAddRequest build() {
			return new MemberAddRequest(access, roles, nick, mute, deafen);
		}
	}

	private final String access_token;
	private final String[] roles;
	private final String nick;
	private final Boolean mute;
	private final Boolean deaf;

	MemberAddRequest(String accessToken, IRole[] roles, String nick, Boolean mute, Boolean deaf) {
		this.access_token = accessToken;
		this.roles = roles == null ? null : Arrays.stream(roles).map(IRole::getStringID).distinct().toArray(String[]::new);
		this.nick = nick;
		this.mute = mute;
		this.deaf = deaf;
	}

	public String getAccessToken() {
		return access_token;
	}

	public String[] getRoles() {
		return roles;
	}

	public String getNick() {
		return nick;
	}

	public Boolean getMute() {
		return mute;
	}

	public Boolean getDeaf() {
		return deaf;
	}
}
