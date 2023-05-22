package com.hawolt.xmpp.event.events;

import com.hawolt.logger.Logger;
import com.hawolt.xmpp.event.AbstractEvent;
import com.hawolt.xmpp.event.GenericEvent;
import com.hawolt.xmpp.event.objects.friends.status.FailedFriendStatus;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created: 22/05/2023 12:50
 * Author: Twitter @hawolt
 **/

public class FailedFriendRequestEvent extends GenericEvent<FailedFriendStatus> {
    private FailedFriendStatus interaction;

    public FailedFriendRequestEvent(AbstractEvent event) {
        super(event);
        try {
            JSONObject object = XML.toJSONObject(event.getPlain()).getJSONObject("iq");
            this.interaction = new FailedFriendStatus(object);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    @Override
    public FailedFriendStatus get() {
        return interaction;
    }
}
