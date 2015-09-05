package com.pony.epidroid.api.data;

import org.json.JSONObject;

public class MarkEntry {
    public final String title;
    public final String finalNote;
    public final String date;

    public final JSONObject other;

    public MarkEntry(JSONObject object) {
        this.date = object.optString("date");
        this.title = object.optString("title");
        this.finalNote = object.optString("final_note");

        this.other = object;
    }
}
