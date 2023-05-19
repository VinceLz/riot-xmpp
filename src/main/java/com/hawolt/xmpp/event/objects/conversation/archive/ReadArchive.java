package com.hawolt.xmpp.event.objects.conversation.archive;

import com.hawolt.xmpp.event.BaseObject;

/**
 * Created: 19/05/2023 15:19
 * Author: Twitter @hawolt
 **/

public class ReadArchive extends BaseObject {
    private final String id;

    public ReadArchive(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
