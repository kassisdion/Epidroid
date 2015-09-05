package com.pony.epidroid.api.listeners.base;

import com.pony.epidroid.utils.JSONHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public abstract class ListListener<T> extends BaseListener {
    public abstract void onList(List<T> list);

    protected abstract T convertItem(Object item) throws JSONException;

    @Override
    public void onArray(JSONArray array) throws JSONException {
        List<Object> source = JSONHelper.arrayToList(array);
        List<T> list = new ArrayList<>(source.size());

        for (Object item : source) {
            list.add(this.convertItem(item));
        }

        this.onList(list);
    }
}
