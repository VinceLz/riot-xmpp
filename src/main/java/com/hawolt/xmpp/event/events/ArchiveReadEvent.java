package com.hawolt.xmpp.event.events;

import com.hawolt.logger.Logger;
import com.hawolt.xmpp.event.AbstractEvent;
import com.hawolt.xmpp.event.GenericEvent;
import com.hawolt.xmpp.event.objects.conversation.archive.ReadArchive;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created: 11/04/2022 00:11
 * Author: Twitter @hawolt
 **/

public class ArchiveReadEvent extends GenericEvent<ReadArchive> {
    private ReadArchive archive;

    public ArchiveReadEvent(AbstractEvent event) {
        super(event);
        try {
            JSONObject iq = XML.toJSONObject(event.getPlain()).getJSONObject("iq");
            this.archive = new ReadArchive(iq.getString("id"));
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    @Override
    public ReadArchive get() {
        return archive;
    }
}