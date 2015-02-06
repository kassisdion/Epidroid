package com.pony.epidroid.api;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.pony.epidroid.api.listeners.base.BaseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hervie_g on 1/26/15.
 */
public class ApiRequest extends Request<ApiResponse>
{
    private BaseListener listener;
    private Map<String, String> bodyParams = new HashMap<>();

    public ApiRequest(Method method, String url, BaseListener listener)
    {
        super(method.method, url, listener);
        this.listener = listener;
        listener.request = this;

        this.setTag(Api.TAG);
    }

    public void setTimeout(int timeout)
    {
        this.setRetryPolicy(new DefaultRetryPolicy(timeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected void deliverResponse(ApiResponse response)
    {
        this.listener.onResponse(response);
    }

    @Override
    protected Response<ApiResponse> parseNetworkResponse(NetworkResponse response)
    {
        return parseResponse(response);
    }

    public static Response<ApiResponse> parseResponse(NetworkResponse response)
    {
        try
        {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            ApiResponse res;
            try
            {
                JSONObject object = new JSONObject(jsonString);

                checkApiError(object);

                res = ApiResponse.newObject(object);
            }
            catch (JSONException _e)
            {
                try
                {
                    JSONArray array = new JSONArray(jsonString);

                    res = ApiResponse.newArray(array);
                }
                catch (JSONException e)
                {
                    return Response.error(new ParseError(e));
                }
            }
            catch (ApiError e)
            {
                return Response.error(e);
            }
            return Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
    }

    private static void checkApiError(JSONObject object) throws ApiError
    {
        JSONObject errorObject = object.optJSONObject("error");

        if (errorObject != null)
        {
            throw new ApiError(errorObject);
        }
    }

    public Map<String, String> getBodyParams()
    {
        return this.bodyParams;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return this.getBodyParams();
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        try
        {
            String body = getBodyString();
            if (body != null)
            {
                return body.getBytes(getParamsEncoding());
            }

            return null;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("Encoding not supported: " + getParamsEncoding(), e);
        }
    }

    public String getBodyString() throws AuthFailureError, UnsupportedEncodingException
    {
        Map<String, String> params = getParams();
        if (params != null && !params.isEmpty())
        {
            return encodeParams(params, getParamsEncoding());
        }
        return null;
    }

    private static String encodeParams(Map<String, String> params,
                                       String encoding) throws UnsupportedEncodingException
    {
        StringBuilder buf = new StringBuilder();

        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            String key = URLEncoder.encode(entry.getKey(), encoding);
            String value = URLEncoder.encode(entry.getValue(), encoding);

            if (!first)
            {
                buf.append('&');
            }
            buf.append(key);
            buf.append('=').append(value);

            first = false;
        }

        return buf.toString();
    }

    public static enum Method
    {
        GET(Request.Method.GET),
        POST(Request.Method.POST),
        DELETE(Request.Method.DELETE),
        PUT(Request.Method.PUT);

        public int method;

        Method(int method)
        {
            this.method = method;
        }
    }

    public static class ApiError extends VolleyError
    {
        public final JSONObject error;

        public ApiError(JSONObject error)
        {
            super(buildErrorMessage(error));
            this.error = error;
        }

        private static String buildErrorMessage(JSONObject error)
        {
            StringBuilder buf = new StringBuilder();

            buf.append("API error (code ").append(error.optInt("code"));
            buf.append("): ").append(error.optString("message"));

            return buf.toString();
        }
    }
}
