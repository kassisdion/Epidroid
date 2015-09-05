package com.pony.epidroid.api;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiResponse {
    public JSONObject object = null;
    public JSONArray array = null;

    public ApiResponse() {
    }

    public static ApiResponse newObject(JSONObject object) {
        ApiResponse response = new ApiResponse();
        response.object = object;
        return response;
    }

    public static ApiResponse newArray(JSONArray array) {
        ApiResponse response = new ApiResponse();
        response.array = array;
        return response;
    }

    public boolean isObject() {
        return this.object != null;
    }

    public boolean isArray() {
        return this.array != null;
    }
}
