package com.pony.epidroid.utils;

import android.content.Context;

import com.pony.epidroid.preferences.PreferencesManager;

/**
 * Created by faisan_f on 03/02/2015.
 */
public class PreferencesManagerHelper {
    public static boolean putString(Context context, String tag, String data) {
        return new PreferencesManager(context).put(tag, data);
    }

    public static String getString(Context context, String tag) {
        return new PreferencesManager(context).getPrefs().getString(tag, null);
    }

    public static boolean contains(Context context, String tag) {
        return new PreferencesManager(context).contains(tag);
    }
}
