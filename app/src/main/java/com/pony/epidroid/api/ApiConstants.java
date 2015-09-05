package com.pony.epidroid.api;

public class ApiConstants {
    public final static String LOGIN_PATH = "/login";
    public final static String INFOS_PATH = "/infos";
    public final static String PLANNING_PATH = "/planning";
    public final static String SUSIES_PATH = "/susies";
    public final static String SUSIE_PATH = "/susie";
    public final static String PROJECTS_PATH = "/projects";
    public final static String PROJECT_PATH = "/project";
    public final static String PROJECT_FILES_PATH = "/project/files";
    public final static String MODULES_PATH = "/modules";
    public final static String ALL_MODULES_PATH = "/allmodules";
    public final static String MODULE_PATH = "/module";
    public final static String EVENT_PATH = "/event";
    public final static String MARKS_PATH = "/marks";
    public final static String MESSAGES_PATH = "/messages";
    public final static String ALERTS_PATH = "/alerts";
    public final static String PHOTO_PATH = "/photo";
    public final static String TOKEN_PATH = "/token";
    public final static String TROMBI_PATH = "/trombi";
    private final static String BASE_URL = "https://epitech-api.herokuapp.com";
    public final static String LOGIN_URL = makeUrl(LOGIN_PATH);
    public final static String INFOS_URL = makeUrl(INFOS_PATH);
    public final static String PLANNING_URL = makeUrl(PLANNING_PATH);
    public final static String SUSIES_URL = makeUrl(SUSIES_PATH);
    public final static String SUSIE_URL = makeUrl(SUSIE_PATH);
    public final static String PROJECTS_URL = makeUrl(PROJECTS_PATH);
    public final static String PROJECT_URL = makeUrl(PROJECT_PATH);
    public final static String PROJECT_FILES_URL = makeUrl(PROJECT_FILES_PATH);
    public final static String MODULES_URL = makeUrl(MODULES_PATH);
    public final static String ALL_MODULES_URL = makeUrl(ALL_MODULES_PATH);
    public final static String MODULE_URL = makeUrl(MODULE_PATH);
    public final static String EVENT_URL = makeUrl(EVENT_PATH);
    public final static String MARKS_URL = makeUrl(MARKS_PATH);
    public final static String MESSAGES_URL = makeUrl(MESSAGES_PATH);
    public final static String ALERTS_URL = makeUrl(ALERTS_PATH);
    public final static String PHOTO_URL = makeUrl(PHOTO_PATH);
    public final static String TOKEN_URL = makeUrl(TOKEN_PATH);
    public final static String TROMBI_URL = makeUrl(TROMBI_PATH);

    public static String makeUrl(String path) {
        return BASE_URL + path;
    }
}
