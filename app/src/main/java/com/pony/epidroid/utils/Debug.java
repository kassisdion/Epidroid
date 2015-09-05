package com.pony.epidroid.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by faisant on 21/01/15.
 */
public class Debug {
    private final static String TAG = "EpiDroid";
    private final static boolean DEBUG = true;

    public static void log(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
