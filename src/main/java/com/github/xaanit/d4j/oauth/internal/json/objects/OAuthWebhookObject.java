package com.github.xaanit.d4j.oauth.internal.json.objects;

import sx.blah.discord.api.internal.json.objects.WebhookObject;

/** Since apparently there's a URL field? In OAuth webhooks? */
public class OAuthWebhookObject extends WebhookObject {
	public String url;
}
