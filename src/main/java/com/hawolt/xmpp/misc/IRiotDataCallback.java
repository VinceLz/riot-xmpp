package com.hawolt.xmpp.misc;

import java.util.function.Supplier;

/**
 * Created: 10/04/2022 16:44
 * Author: Twitter @hawolt
 **/

public interface IRiotDataCallback {
    String getUsername();
    String getPassword();

    String getGameRegion();

    String getXMPPToken();

    String getIdentifier();

    RiotChatServer getChatServer();

    String getAccessToken();

    String getEntitlementToken();

    Supplier<String> getEntitlementSupplier();

    Supplier<String> getAccessTokenSupplier();
}
