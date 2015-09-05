package com.pony.epidroid.api.listeners.derived;

import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class JSONObjectListListener extends ListListener<JSONObject> {
    @Override
    protected JSONObject convertItem(Object item) throws JSONException {
        return (JSONObject) item;
    }
}
