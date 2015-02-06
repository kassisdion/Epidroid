package com.pony.epidroid.utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hervie_g on 1/27/15.
 */
public class JSONHelper
{
    public static <T> List<T> arrayToList(JSONArray array) throws JSONException
    {
        List<T> list = new ArrayList<>(array != null ? array.length() : 0);
        addArrayToList(list, array);
        return list;
    }

    public static <T> void addArrayToList(List<T> list, JSONArray array) throws JSONException
    {
        if (array != null)
        {
            for (int i = 0; i < array.length(); i++)
            {
                T item = (T) array.get(i);
                list.add(item);
            }
        }
    }
}
