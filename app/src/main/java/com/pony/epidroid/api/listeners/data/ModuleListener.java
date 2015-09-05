package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ModuleListener extends BaseListener {
    public abstract void onModule(JSONObject module);

    @Override
    public void onObject(JSONObject object) throws JSONException {
        this.onModule(object);
    }
}
