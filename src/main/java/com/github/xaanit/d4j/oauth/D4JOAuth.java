package com.github.xaanit.d4j.oauth;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class D4JOAuth implements IModule {

    public boolean enable(IDiscordClient client) {
        return true;
    }

    public void disable() {

    }

    public String getName() {
        return "D4J-OAuth";
    }

    public String getAuthor() {
        return "xaanit";
    }

    public String getVersion() {
        return "0.1";
    }

    public String getMinimumDiscord4JVersion() {
        return "2.7.0";
    }
}
