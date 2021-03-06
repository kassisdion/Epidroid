package com.pony.epidroid.api.listeners.derived;

import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONObjectListener extends BaseListener {
    public abstract void onJSONObject(JSONObject object);

    @Override
    public void onObject(JSONObject object) throws JSONException {
        this.onJSONObject(object);
    }
}
