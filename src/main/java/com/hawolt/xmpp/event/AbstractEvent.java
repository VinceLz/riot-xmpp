package com.hawolt.xmpp.event;

import com.hawolt.xmpp.core.output.IOutput;
import com.hawolt.xmpp.event.objects.connection.ChatIdentity;
import com.hawolt.xmpp.misc.IRiotDataCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created: 11/04/2022 00:06
 * Author: Twitter @hawolt
 **/

public class AbstractEvent {
    private final static Pattern pattern = Pattern.compile("type='(.*?)'");
    private final IRiotDataCallback callback;
    private final ChatIdentity identity;
    private final String name, plain;
    private final EventType type;
    private final IOutput output;

    public AbstractEvent(IOutput output, String name, String plain, ChatIdentity identity, IRiotDataCallback callback) {
        this.callback = callback;
        this.identity = identity;
        this.output = output;
        this.plain = plain;
        this.name = name;
        if ("pending_out".equals(name) || "pending_in".equals(name) || "remove".equals(name) || "both".equals(name)) {
            this.type = EventType.FRIEND_REQUEST;
        } else if (name != null && name.startsWith("get_archive")) {
            this.type = EventType.MESSAGE_HISTORY;
        } else if (name != null && name.startsWith("set_archive_read")) {
            this.type = EventType.ARCHIVE_READ;
        } else if (name != null && name.startsWith("roster_")) {
            Matcher matcher = pattern.matcher(plain);
            if (matcher.find() && matcher.group(1).equals("error")) {
                this.type = EventType.FRIEND_REQUEST_FAILED;
            } else {
                this.type = EventType.FRIEND_REQUEST_STATUS;
            }
        } else if (name != null && name.startsWith("message")) {
            this.type = EventType.MESSAGE;
        } else if ("presence".equals(name)) {
            this.type = EventType.PRESENCE;
        } else if ("recent_convos_4".equals(name)) {
            this.type = EventType.RECENT_CONVERSATIONS;
        } else if ("2".equals(name)) {
            this.type = EventType.FRIEND_LIST;
        } else if ("update_client_active_5".equals(name)) {
            this.type = EventType.ON_READY;
        } else if ("_xmpp_session1".equals(name)) {
            this.type = EventType.SUMMONER_NAME;
        } else if ("_xmpp_bind1".equals(name)) {
            this.type = EventType.PERSONAL_INFO;
        } else if ("xmpp_entitlements_0".equals(name)) {
            this.type = EventType.INVALID_TOKEN;
        } else if ("stream:error".equals(name)) {
            this.type = EventType.SESSION_EXPIRED;
        } else if ("failure".equals(name)) {
            this.type = EventType.FAILURE;
        } else {
            this.type = EventType.UNKNOWN;
        }
    }

    public IRiotDataCallback getCallback() {
        return callback;
    }

    public IOutput getOutput() {
        return output;
    }

    public ChatIdentity getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public String getPlain() {
        return plain;
    }

    public EventType getType() {
        return type;
    }
}
