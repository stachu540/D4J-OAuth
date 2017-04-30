package com.github.xaanit.d4j.oauth.handle.impl.events;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.internal.json.objects.WebhookObject;

/**
 * Used when an webhook is created for an unknown/invisible/hidden channel
 */
public class OAuthWebhookCreate extends Event {
	private final WebhookObject webhook;

	public OAuthWebhookCreate(WebhookObject webhook) {
		this.webhook = webhook;
	}

	public WebhookObject getWebhook() {
		return webhook;
	}
}
