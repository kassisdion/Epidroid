package com.pony.epidroid.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityHelper {
    public static <T extends Activity> void startActivity(Activity parent, Class<T> clazz,
                                                          boolean killParent) {
        Intent intent = new Intent(parent, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        parent.startActivity(intent);

        if (killParent) {
            parent.finish();
        }
    }
}
