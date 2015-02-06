package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class PhotoListener extends BaseListener
{
    public abstract void onPhoto(String url);

    @Override
    public void onObject(JSONObject object) throws JSONException
    {
        this.onPhoto(object.getString("url"));
    }
}