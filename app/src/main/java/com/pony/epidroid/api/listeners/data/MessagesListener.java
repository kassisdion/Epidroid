package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public abstract class MessagesListener extends ListListener<JSONObject> {
    public abstract void onMessages(List<JSONObject> messages);

    @Override
    public void onList(List<JSONObject> list) {
        this.onMessages(list);
    }

    @Override
    protected JSONObject convertItem(Object item) throws JSONException {
        return (JSONObject) item;
    }
}
