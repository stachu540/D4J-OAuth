

# DISCONTINUED. FEEL FREE TO PICK BACK UP.

# D4J-OAuth
Java interface for Discord's OAuth API, integrated into [Discord4J](https://github.com/austinv11/Discord4J). 



[Support Server](https://discord.gg/FCXNeUq)

# How to use
D4J-OAuth is designed to be as simple to implement as possible, while still providing the power to do cool stuff. NOTE: **YOU MUST BE ON DISCORD4J 2.8.1 OR ABOVE**

To start, you'll want to create an instance of `DiscordOAuthBuilder`, and provide it a client id, client secret, a redirect URL, and at least one scope. For example

```java
new DiscordOAuthBuilder(client)
				.withClientID(args[1])
				.withClientSecret(args[2])
				.withRedirectUrl("http://localhost:8080/callback")
				.withHttpServerOptions(new HttpServerOptions().setPort(8080))
				.withScopes(Scope.IDENTIFY, Scope.CONNECTIONS)
				.build()
```

This will create an instanceof of IDiscordOAuth bound to port 8080, with the client ID set to `args[1]` and the secret set to `args[2]`.

# Example Project
* [Butler Bot](https://github.com/UnderMybrella/ButlerBot) is a very simple bot written to demonstrate basic interaction with the API.

# Download

```
<dependency>
            <groupId>com.github.xaanit</groupId>
            <artifactId>D4J-OAuth</artifactId>
            <version>master-SNAPSHOT</version>
</dependency>
```

`master-SNAPSHOT` can also be substituted for a GitHub commit.


