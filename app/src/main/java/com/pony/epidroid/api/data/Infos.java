package com.pony.epidroid.api.data;

import com.pony.epidroid.utils.JSONHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hervie_g on 1/26/15.
 */
public class Infos
{
    public final Board board;
    public final JSONObject current;
    public final List<JSONObject> history;
    public final JSONObject infos;
    public final String ip;

    public Infos(JSONObject object) throws JSONException
    {
        this.board = new Board(object.getJSONObject("board"));
        this.current = object.getJSONObject("current");
        this.history = JSONHelper.arrayToList(object.optJSONArray("history"));
        this.infos = object.getJSONObject("infos");
        this.ip = object.getString("ip");
    }

    public class Board
    {
        public final List<JSONObject> activities;
        public final List<JSONObject> modules;
        public final List<JSONObject> marks;
        public final List<JSONObject> projects;
        public final List<JSONObject> internships;
        public final List<JSONObject> susies;
        public final List<JSONObject> tickets;

        public Board(JSONObject object) throws JSONException
        {
            this.activities = JSONHelper.arrayToList(object.optJSONArray("activites"));
            this.modules = JSONHelper.arrayToList(object.optJSONArray("modules"));
            this.marks = JSONHelper.arrayToList(object.optJSONArray("notes"));
            this.projects = JSONHelper.arrayToList(object.optJSONArray("projets"));
            this.internships = JSONHelper.arrayToList(object.optJSONArray("stages"));
            this.susies = JSONHelper.arrayToList(object.optJSONArray("susies"));
            this.tickets = JSONHelper.arrayToList(object.optJSONArray("tickets"));
        }
    }
}
