package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class LoginListener extends BaseListener {
    public abstract void onSuccess(String token);

    @Override
    public void onObject(JSONObject object) throws JSONException {
        String token = object.getString("token");

        this.onSuccess(token);
    }
}
