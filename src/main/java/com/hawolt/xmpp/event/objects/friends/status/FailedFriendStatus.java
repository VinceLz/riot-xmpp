package com.hawolt.xmpp.event.objects.friends.status;

import org.json.JSONObject;

import java.util.stream.Collectors;

/**
 * Created: 12/04/2022 02:06
 * Author: Twitter @hawolt
 **/

public class FailedFriendStatus extends FriendStatus {
    private final String result, reason;
    private final int code;

    public FailedFriendStatus(JSONObject o) {
        super(o);
        JSONObject error = o.getJSONObject("error");
        this.code = error.getInt("code");
        this.result = error.getString("type");
        if (code >= 500) {
            this.reason = error.keySet().stream().filter(k -> k.contains("-")).collect(Collectors.joining());
        } else {
            this.reason = error.getJSONObject("text").getString("content");
        }
    }

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "FailedFriendStatus{" +
                "result='" + result + '\'' +
                ", code=" + code +
                ", reason='" + reason + '\'' +
                '}';
    }
}
