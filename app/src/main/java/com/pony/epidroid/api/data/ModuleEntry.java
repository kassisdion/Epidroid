package com.pony.epidroid.api.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dusterherz on 03/02/15.
 */
public class ModuleEntry {
    public final String start;
    public final int scholarYear;
    public final String codeModule;
    public final String codeInstance;
    public final String title;
    public final String cycle;
    public final String grade;
    public final int credits;
    public final int semester;
    public final JSONObject other;

    public ModuleEntry(JSONObject object) throws JSONException {
        this.title = object.getString("title");
        this.start = object.getString("date_ins");
        this.scholarYear = object.getInt("scolaryear");
        this.codeModule = object.getString("codemodule");
        this.codeInstance = object.getString("codeinstance");
        this.cycle = object.getString("cycle");
        this.grade = object.getString("grade");
        this.credits = object.getInt("credits");
        this.semester = object.getInt("semester");
        this.other = object;
    }
}
