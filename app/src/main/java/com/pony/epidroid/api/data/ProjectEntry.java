package com.pony.epidroid.api.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by faisan_f on 03/02/2015.
 */
public class ProjectEntry {
    public final String project;
    public final String titleModule;
    public final Boolean registered;
    public final JSONObject other;

    public ProjectEntry(JSONObject object) throws JSONException {
        this.project = object.getString("project");
        this.titleModule = object.getString("title_module");
        this.registered = object.getInt("registered") != 0;
        this.other = object;
    }
}
