package com.github.xaanit.d4j.oauth.internal.json.objects;

public class AuthorizeUserResponse {
	public String token_type;
	public String access_token;
	public String scope;
	public long expires_in;
	public long expires_at;
	public String refresh_token;
	public OAuthWebhookObject webhook;
}
