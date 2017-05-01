package com.github.xaanit.d4j.oauth.handle.impl.events;

import com.github.xaanit.d4j.oauth.handle.IOAuthWebhook;
import sx.blah.discord.api.events.Event;

/**
 * Used when an webhook is created for an unknown/invisible/hidden channel
 */
public class OAuthWebhookCreate extends Event {
	private final IOAuthWebhook webhook;

	public OAuthWebhookCreate(IOAuthWebhook webhook) {
		this.webhook = webhook;
	}

	public IOAuthWebhook getWebhook() {
		return webhook;
	}
}
