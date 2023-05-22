package com.hawolt.xmpp.event.objects.connection;

import com.hawolt.xmpp.event.BaseObject;
import org.json.JSONObject;

/**
 * Created: 22/05/2023 13:03
 * Author: Twitter @hawolt
 **/

public class BadToken extends BaseObject {
    private final String content;
    private final int code;

    public BadToken(JSONObject object) {
        JSONObject error = object.getJSONObject("error");
        this.code = error.getInt("code");
        JSONObject text = error.getJSONObject("text");
        this.content = text.getString("content");
    }

    public String getContent() {
        return content;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BadToken{" +
                "content='" + content + '\'' +
                ", code=" + code +
                '}';
    }
}
