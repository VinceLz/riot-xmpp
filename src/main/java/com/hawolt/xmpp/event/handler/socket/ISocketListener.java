package com.hawolt.xmpp.event.handler.socket;

/**
 * Created: 20/04/2022 11:23
 * Author: Twitter @hawolt
 **/

public interface ISocketListener {

    String getConnectionIdentifier();

    void onSessionRefreshFail();

    void onConnectionIssue();

    void onSessionExpired();

    void onTermination();
}
