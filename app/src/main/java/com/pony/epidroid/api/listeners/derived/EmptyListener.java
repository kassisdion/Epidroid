package com.pony.epidroid.api.listeners.derived;

import com.pony.epidroid.api.ApiResponse;
import com.pony.epidroid.api.listeners.base.BaseListener;

public abstract class EmptyListener extends BaseListener {
    public abstract void onSuccess();

    @Override
    public void onResponse(ApiResponse response) {
        this.onSuccess();
    }
}
