package cz.memsource.assignment.utils;

import com.squareup.okhttp.MediaType;

public class Const {

    private Const() {

    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String HTTPS_MEMSOURCE = "https://cloud.memsource.com";
    public static final String HTTP_LOCALHOST = "http://localhost";
    public static final String CLIENT_URL = "http://localhost:4200";

    public static final String MEMSOURCE_LOGIN_URL = HTTPS_MEMSOURCE + "/web/api/v3/auth/login";
    public static final String MEMSOURCE_PROJECTS_URL = HTTPS_MEMSOURCE + "/web/api/v4/project/list";
    public static final String ENDPOINT_LOGIN_MAPPING = "/api/v1/log";
    public static final String ENDPOINT_PROJECTS_MAPPING = "/api/v1/projects";

    public static final String MEMSOURCE_API_USER_NAME = "userName";
    public static final String MEMSOURCE_API_PASSWORD = "password";
    public static final String MEMSOURCE_API_TOKEN = "token";


    public static final String SESSION_ATTRIBUTE_TOKEN = "token";
    public static final String SESSION_ATTRIBUTE_OWNER_ID = "ownerId";
}
