package com.pony.epidroid.api;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hervie_g on 1/26/15.
 */
public class ApiResponse
{
    public JSONObject object = null;
    public JSONArray array = null;

    public ApiResponse()
    {
    }

    public boolean isObject()
    {
        return this.object != null;
    }

    public boolean isArray()
    {
        return this.array != null;
    }

    public static ApiResponse newObject(JSONObject object)
    {
        ApiResponse response = new ApiResponse();
        response.object = object;
        return response;
    }

    public static ApiResponse newArray(JSONArray array)
    {
        ApiResponse response = new ApiResponse();
        response.array = array;
        return response;
    }
}
