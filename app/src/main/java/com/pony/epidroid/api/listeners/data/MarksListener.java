package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.data.MarkEntry;
import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class MarksListener extends ListListener<MarkEntry> {
    public abstract void onMarks(List<MarkEntry> marks);

    @Override
    protected MarkEntry convertItem(Object item) throws JSONException {
        JSONObject object = (JSONObject) item;
        return new MarkEntry(object);
    }

    @Override
    public void onList(List<MarkEntry> list) {
        this.onMarks(list);
    }

    @Override
    public void onObject(JSONObject object) throws JSONException {
        JSONArray array = object.getJSONArray("notes");
        this.onArray(array);
    }
}
