package com.hawolt.xmpp.event.events;

import com.hawolt.logger.Logger;
import com.hawolt.xmpp.event.AbstractEvent;
import com.hawolt.xmpp.event.GenericEvent;
import com.hawolt.xmpp.event.objects.connection.BadToken;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created: 22/05/2023 12:50
 * Author: Twitter @hawolt
 **/

public class InvalidTokenEvent extends GenericEvent<BadToken> {
    private BadToken token;

    public InvalidTokenEvent(AbstractEvent event) {
        super(event);
        try {
            JSONObject object = XML.toJSONObject(event.getPlain()).getJSONObject("iq");
            this.token = new BadToken(object);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    @Override
    public BadToken get() {
        return token;
    }
}
