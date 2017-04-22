package com.github.xaanit.d4j.oauth;

public enum Scope {
	BOT("bot"),
	CONNECTIONS("connections"),
	EMAIL("email"),
	IDENTIFY("identify"),
	GUILDS("guilds"),
	GUILDS_JOIN("guilds.join"),
	GDM_JOIN("gdm.join"),
	MESSAGES_READ("message.read"),
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

}
