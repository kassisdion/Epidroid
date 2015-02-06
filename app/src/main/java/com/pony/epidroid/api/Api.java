package com.pony.epidroid.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.pony.epidroid.api.listeners.base.BaseListener;
import com.pony.epidroid.api.listeners.data.AlertsListener;
import com.pony.epidroid.api.listeners.data.EventListener;
import com.pony.epidroid.api.listeners.data.InfosListener;
import com.pony.epidroid.api.listeners.data.LoginListener;
import com.pony.epidroid.api.listeners.data.MarksListener;
import com.pony.epidroid.api.listeners.data.MessagesListener;
import com.pony.epidroid.api.listeners.data.ModuleListener;
import com.pony.epidroid.api.listeners.data.ModulesListener;
import com.pony.epidroid.api.listeners.data.PhotoListener;
import com.pony.epidroid.api.listeners.data.PlanningListener;
import com.pony.epidroid.api.listeners.data.ProjectListener;
import com.pony.epidroid.api.listeners.data.ProjectsListener;
import com.pony.epidroid.api.listeners.data.SusieListener;
import com.pony.epidroid.api.listeners.derived.EmptyListener;
import com.pony.epidroid.api.listeners.derived.JSONObjectListListener;
import com.pony.epidroid.api.listeners.derived.JSONObjectListener;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hervie_g on 1/26/15.
 */
public class Api
{
    public final static String TAG = "EpitechApi";
    private static RequestQueue queue = null;

    /**
     * You must call this method before doing any request.
     *
     * @param context
     */
    public static void reset(Context context)
    {
        Api.queue = Volley.newRequestQueue(context);
    }

    public static void reset()
    {
        Api.queue = null;
    }

    public static void cancelAll()
    {
        if (Api.queue != null)
        {
            Api.queue.cancelAll(TAG);
        }
    }

    private static void doRequest(RequestInfo info, BaseListener listener)
    {
        ApiRequest request = info.createRequest(listener);
        try
        {
            System.err.println("Sending to " + request.getUrl() + ": " + request.getBodyString());
        }
        catch (AuthFailureError | UnsupportedEncodingException authFailureError)
        {
            authFailureError.printStackTrace();
        }
        queue.add(request);
    }

    public static void login(String login, String password, LoginListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.LOGIN_URL)
                .withParam("login", login)
                .withParam("password", password);
        info.timeout = 15000;
        doRequest(info, listener);
    }

    public static void getInfos(InfosListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.INFOS_URL)
                .withToken();
        doRequest(info, listener);
    }

    public static void getPlanning(Date start, Date end, PlanningListener listener)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.PLANNING_URL)
                .withToken()
                .withParam("start", format.format(start))
                .withParam("end", format.format(end));
        doRequest(info, listener);
    }

    public static void getPlanning(Calendar start, Calendar end, PlanningListener listener)
    {
        getPlanning(start.getTime(), end.getTime(), listener);
    }

    public static void getSusies(Calendar start, Calendar end, SusieStatus status,
                                 PlanningListener listener)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //String statusValue = status.name().toLowerCase();

        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.PLANNING_URL)
                .withToken()
                .withParam("start", format.format(start.getTime()))
                .withParam("end", format.format(end.getTime()))
                .withParam("get", status.name().toLowerCase());
        doRequest(info, listener);
    }

    public static void getSusies(Calendar start, Calendar end, PlanningListener listener)
    {
        getSusies(start, end, SusieStatus.ALL, listener);
    }

    public static void getSusie(int id, SusieListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.SUSIE_URL)
                .withToken()
                .withParam("id", String.valueOf(id));
        doRequest(info, listener);
    }

    public static void subscribeSusie(int id, EmptyListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.SUSIE_URL)
                .withToken()
                .withParam("id", String.valueOf(id));
        doRequest(info, listener);
    }

    public static void unsubscribeSusie(int id, EmptyListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.DELETE, ApiConstants.SUSIE_URL)
                .withToken()
                .withParam("id", String.valueOf(id));
        doRequest(info, listener);
    }

    public static void getProjects(ProjectsListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.PROJECTS_URL)
                .withToken();
        doRequest(info, listener);
    }

    public static void getProject(int year, String module, String instance, String activity,
                                  ProjectListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.PROJECT_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity);
        doRequest(info, listener);
    }

    public static void subscribeProject(int year, String module, String instance, String activity,
                                        JSONObjectListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.PROJECT_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity);
        doRequest(info, listener);
    }

    public static void unsubscribeProject(int year, String module, String instance, String activity,
                                          EmptyListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.DELETE, ApiConstants.PROJECT_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity);
        doRequest(info, listener);
    }

    public static void getProjectFiles(int year, String module, String instance, String activity,
                                       JSONObjectListListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.DELETE, ApiConstants.PROJECT_FILES_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity);
        doRequest(info, listener);
    }

    public static void getModules(ModulesListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.MODULES_URL)
                .withToken();
        doRequest(info, listener);
    }

    public static void getAllModules(int year, String location, String course,
                                     ModulesListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.ALL_MODULES_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("location", location)
                .withParam("course", course);
        doRequest(info, listener);
    }

    public static void getModule(int year, String module, String instance, ModuleListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.MODULE_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance);
        doRequest(info, listener);
    }

    public static void subscribeModule(int year, String module, String instance,
                                       JSONObjectListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.MODULE_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance);
        doRequest(info, listener);
    }

    public static void unsubscribeModule(int year, String module, String instance,
                                         JSONObjectListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.DELETE, ApiConstants.MODULE_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance);
        doRequest(info, listener);
    }

    public static void getEvent(int year, String module, String instance, String activity,
                                String event, EventListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.EVENT_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity)
                .withParam("codeevent", event);
        doRequest(info, listener);
    }

    public static void subscribeEvent(int year, String module, String instance, String activity,
                                      String event, EmptyListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.EVENT_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity)
                .withParam("codeevent", event);
        doRequest(info, listener);
    }

    public static void unsubscribeEvent(int year, String module, String instance, String activity,
                                        String event, EmptyListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.DELETE, ApiConstants.EVENT_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity)
                .withParam("codeevent", event);
        doRequest(info, listener);
    }

    public static void getMarks(MarksListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.MARKS_URL)
                .withToken();
        doRequest(info, listener);
    }

    public static void getMessages(MessagesListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.GET, ApiConstants.MESSAGES_URL)
                .withToken();
        doRequest(info, listener);
    }

    public static void getAlerts(AlertsListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.ALERTS_URL)
                .withToken();
        doRequest(info, listener);
    }

    public static void getPhoto(String login, PhotoListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.PHOTO_URL)
                .withToken()
                .withParam("login", login);
        doRequest(info, listener);
    }

    public static void validateToken(int year, String module, String instance, String activity,
                                     String token, EmptyListener listener)
    {
        RequestInfo info = new RequestInfo(ApiRequest.Method.POST, ApiConstants.TOKEN_URL)
                .withToken()
                .withParam("scolaryear", String.valueOf(year))
                .withParam("codemodule", module)
                .withParam("codeinstance", instance)
                .withParam("codeacti", activity)
                .withParam("tokenvalidationcode", token);
        doRequest(info, listener);
    }

    public static enum SusieStatus
    {
        ALL,
        FREE,
        REGISTERED,
    }
}
