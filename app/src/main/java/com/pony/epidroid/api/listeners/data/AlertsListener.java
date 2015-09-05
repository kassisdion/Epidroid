package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public abstract class AlertsListener extends ListListener<String> {
    public abstract void onAlerts(List<String> alerts);

    @Override
    public void onList(List<String> list) {
        this.onAlerts(list);
    }

    @Override
    protected String convertItem(Object item) throws JSONException {
        JSONObject object = (JSONObject) item;
        return object.optString("title");
    }
}
