package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.data.PlanningEntry;
import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class PlanningListener extends ListListener<PlanningEntry> {
    public abstract void onPlanning(List<PlanningEntry> planning);

    @Override
    protected PlanningEntry convertItem(Object item) throws JSONException {
        JSONObject object = (JSONObject) item;
        return new PlanningEntry(object);
    }

    @Override
    public void onList(List<PlanningEntry> list) {
        this.onPlanning(list);
    }
}
