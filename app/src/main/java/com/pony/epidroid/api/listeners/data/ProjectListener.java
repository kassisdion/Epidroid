package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ProjectListener extends BaseListener {
    public abstract void onProject(JSONObject project);

    @Override
    public void onObject(JSONObject object) throws JSONException {
        this.onProject(object);
    }
}
