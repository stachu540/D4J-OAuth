package com.github.xaanit.d4j.oauth.util;

import com.github.xaanit.d4j.oauth.Scope;

public class MissingScopeException extends RuntimeException {
	private final Scope missing;

	public MissingScopeException(Scope missing) {
		super(getMessage(missing));
		this.missing = missing;
	}

	public Scope getMissingScope() {
		return missing;
	}

	private static String getMessage(Scope missing) {
		return "Missing scope: " + missing.getName();
	}
}
