package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class SusieListener extends BaseListener
{
    public abstract void onSusie(JSONObject susie);

    @Override
    public void onObject(JSONObject object) throws JSONException
    {
        this.onSusie(object);
    }
}
