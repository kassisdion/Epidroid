package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.data.Infos;
import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class InfosListener extends BaseListener {
    public abstract void onInfos(Infos infos) throws JSONException;

    @Override
    public void onObject(JSONObject object) throws JSONException {
        this.onInfos(new Infos(object));
    }
}
