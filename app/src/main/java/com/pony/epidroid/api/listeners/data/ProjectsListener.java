package com.pony.epidroid.api.listeners.data;

import com.pony.epidroid.api.data.ProjectEntry;
import com.pony.epidroid.api.listeners.base.ListListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class ProjectsListener extends ListListener<ProjectEntry>
{
    public abstract void onProjects(List<ProjectEntry> modules);

    @Override
    protected ProjectEntry convertItem(Object item) throws JSONException
    {
        JSONObject object = (JSONObject) item;
        return new ProjectEntry(object);
    }

    @Override
    public void onList(List<ProjectEntry> list)
    {
        this.onProjects(list);
    }

    @Override
    public void onObject(JSONObject object) throws JSONException
    {
        JSONArray array = object.getJSONArray("modules");
        this.onArray(array);
    }
}
