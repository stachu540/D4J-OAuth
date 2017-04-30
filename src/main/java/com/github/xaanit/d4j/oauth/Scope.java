package com.github.xaanit.d4j.oauth;

import java.util.EnumSet;

public enum Scope {
	BOT("bot"),
	CONNECTIONS("connections"),
	EMAIL("email"),
	IDENTIFY("identify"),
	GUILDS("guilds"),
	GUILDS_JOIN("guilds.join"),
	GDM_JOIN("gdm.join"),
	MESSAGES_READ("messages.read"),
	/** Warning: Errors with invalid_scope, possibly due to RPC whitelisting */
	RPC("rpc"),
	RPC_API("rpc.api"),
	RPC_NOTIFICATIONS_READ("rpc.notifications.read"),
	WEBHOOK_INCOMING("webhook.incoming");

	private String name;

	Scope(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Scope forName(String name) {
		for(Scope scope : values())
			if(scope.getName().equalsIgnoreCase(name))
				return scope;
		throw new IllegalArgumentException("No scope with the name " + name);
	}

	public static EnumSet<Scope> getScopes(String scope) {
		EnumSet<Scope> scopes = EnumSet.noneOf(Scope.class);

		for(String individualScope : scope.split(" "))
			scopes.add(Scope.forName(individualScope));

		return scopes;
	}
}
