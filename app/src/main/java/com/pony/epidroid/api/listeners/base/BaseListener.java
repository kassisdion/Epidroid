package com.pony.epidroid.api.listeners.base;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pony.epidroid.api.ApiRequest;
import com.pony.epidroid.api.ApiResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hervie_g on 1/26/15.
 */
public abstract class BaseListener implements Response.Listener<ApiResponse>, Response.ErrorListener {
    public ApiRequest request = null;

    private static VolleyError parseError(NetworkResponse response) {
        Response<ApiResponse> res = ApiRequest.parseResponse(response);
        return res.error;
    }

    public abstract void onError(VolleyError error);

    @Override
    public void onErrorResponse(VolleyError error) {
        if (error instanceof AuthFailureError) {
            error = parseError(error.networkResponse);
        }

        error.printStackTrace();
        this.onError(error);
    }

    @Override
    public void onResponse(ApiResponse response) {
        try {
            if (response.isObject()) {
                this.onObject(response.object);
            }
            else if (response.isArray()) {
                this.onArray(response.array);
            }
        } catch (JSONException e) {
            this.onErrorResponse(new VolleyError(e));
        }
    }

    public void onObject(JSONObject object) throws JSONException {
        this.onError(new BadFormatError("JSON Object", "JSON Array"));
    }

    public void onArray(JSONArray array) throws JSONException {
        this.onError(new BadFormatError("JSON Array", "JSON Object"));
    }

    public static class BadFormatError extends VolleyError {
        public BadFormatError(String format, String expected) {
            super(buildMessage(format, expected));
        }

        private static String buildMessage(String format, String expected) {
            StringBuilder buf = new StringBuilder();

            buf.append("Expected format `").append(expected);
            buf.append("`, got `").append(format);
            buf.append("`");

            return buf.toString();
        }
    }
}