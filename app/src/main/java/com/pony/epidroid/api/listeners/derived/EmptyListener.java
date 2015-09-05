package com.pony.epidroid.api.listeners.derived;

import com.pony.epidroid.api.ApiResponse;
import com.pony.epidroid.api.listeners.base.BaseListener;

/**
 * Created by hervie_g on 1/27/15.
 */
public abstract class EmptyListener extends BaseListener {
    public abstract void onSuccess();

    @Override
    public void onResponse(ApiResponse response) {
        this.onSuccess();
    }
}
