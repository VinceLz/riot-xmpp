package com.hawolt.xmpp.misc.impl;

import com.hawolt.authentication.WebOrigin;
import com.hawolt.cryptography.SHA256;
import com.hawolt.virtual.leagueclient.VirtualLeagueClient;
import com.hawolt.virtual.riotclient.VirtualRiotClient;
import com.hawolt.xmpp.misc.IRiotDataCallback;
import com.hawolt.xmpp.misc.RiotChatServer;
import org.json.JSONObject;

import java.util.Base64;
import java.util.function.Supplier;

/**
 * Created: 24/02/2023 14:25
 * Author: Twitter @hawolt
 **/

public class RiotDataCallback implements IRiotDataCallback {
    private final VirtualLeagueClient virtualLeagueClient;
    private final RiotChatServer riotChatServer;
    private final String username, password, identifier;

    public RiotDataCallback(VirtualLeagueClient virtualLeagueClient) {
        this.virtualLeagueClient = virtualLeagueClient;
        VirtualRiotClient virtualRiotClient = virtualLeagueClient.getVirtualRiotClient();
        JSONObject object = new JSONObject(new String(Base64.getDecoder().decode(getXMPPToken().split("\\.")[1])));
        this.riotChatServer = RiotChatServer.valueOf(object.getString("affinity").replace("-", "_").toUpperCase());
        this.identifier = SHA256.hash(String.join(":", virtualRiotClient.getUsername(), virtualRiotClient.getPassword()));
        this.username = virtualRiotClient.getUsername();
        this.password = virtualRiotClient.getPassword();
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getGameRegion() {
        return virtualLeagueClient.getVirtualLeagueClientInstance().getPlatformId();
    }

    @Override
    public String getXMPPToken() {
        return virtualLeagueClient.getGeoPas().get("xmpp_token");
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public RiotChatServer getChatServer() {
        return riotChatServer;
    }

    @Override
    public String getAccessToken() {
        return virtualLeagueClient.getVirtualLeagueClientInstance().getLeagueClientSupplier().get("lol.access_token", true);
    }

    @Override
    public String getEntitlementToken() {
        return virtualLeagueClient.getEntitlement().get("entitlement.lol.entitlements_token", true);
    }

    @Override
    public Supplier<String> getEntitlementSupplier() {
        return () -> virtualLeagueClient.getEntitlement().get("entitlement.oauthtoken.entitlements_token", true);
    }

    @Override
    public Supplier<String> getAccessTokenSupplier() {
        return () -> virtualLeagueClient.getWebOriginOAuthTokenMap().get(WebOrigin.ENTITLEMENTS).get("oauthtoken.access_token", true);
    }
}
