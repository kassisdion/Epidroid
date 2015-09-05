package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.data.ModuleEntry;
import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public abstract class ModulesListener extends ListListener<ModuleEntry> {
    public abstract void onModules(List<ModuleEntry> modules);

    @Override
    protected ModuleEntry convertItem(Object item) throws JSONException {
        JSONObject object = (JSONObject) item;
        return new ModuleEntry(object);
    }

    @Override
    public void onList(List<ModuleEntry> list) {
        this.onModules(list);
    }

    @Override
    public void onObject(JSONObject object) throws JSONException {
        JSONArray array = object.getJSONArray("modules");
        this.onArray(array);
    }
}
