package com.pony.epidroid.api.data;

import org.json.JSONObject;

/**
 * Created by hervie_g on 2/3/15.
 */
public final class PlanningEntry
{
    public final String start;
    public final String end;
    public final Integer scholarYear;
    public final String codeModule;
    public final String codeActi;
    public final String codeEvent;
    public final String codeInstance;
    public final String actiTitle;
    public final String titleModule;
    public final String eventRegistered;
    public final boolean moduleRegistered;
    public final boolean allowRegister;
    public final boolean allowToken;

    public final JSONObject other;

    public PlanningEntry(JSONObject object)
    {
        this.actiTitle = object.optString("acti_title");
        this.start = object.optString("start");
        this.end = object.optString("end");
        String scolarYear = object.optString("scolaryear");
        this.scholarYear = scolarYear == null || scolarYear.isEmpty() ? null : Integer.parseInt(scolarYear);
        this.codeModule = object.optString("codemodule");
        this.codeActi = object.optString("codeacti");
        this.codeEvent = object.optString("codeevent");
        this.codeInstance = object.optString("codeinstance");
        this.titleModule = object.optString("titlemodule");
        this.eventRegistered = object.optString("event_registered");
        this.moduleRegistered = object.optBoolean("module_registered", true);
        this.allowRegister = object.optBoolean("allow_register");
        this.allowToken = object.optBoolean("allow_token");

        this.other = object;
    }
}
