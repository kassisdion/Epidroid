package com.pony.epidroid.api;

import com.pony.epidroid.api.listeners.base.BaseListener;

import java.util.HashMap;
import java.util.Map;

public class RequestInfo {
    public ApiRequest.Method method;
    public String url;
    public Map<String, String> params;
    public Integer timeout;

    public RequestInfo(ApiRequest.Method method, String url) {
        this.url = url;
        this.method = method;
        params = new HashMap<>();
    }

    public void setParam(String key, String value) {
        this.params.put(key, value);
    }

    public RequestInfo withParam(String key, String value) {
        this.setParam(key, value);
        return this;
    }

    public RequestInfo withToken() {
        this.setParam("token", ApiData.token);
        return this;
    }

    public ApiRequest createRequest(BaseListener listener) {
        ApiRequest request = new ApiRequest(this.method, this.url, listener);
        request.getBodyParams().putAll(this.params);
        if (this.timeout != null) {
            request.setTimeout(this.timeout);
        }
        return request;
    }
}
