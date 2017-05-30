package cz.memsource.assignment.controller;

import cz.memsource.assignment.api.MemsourceLoginResponse;

import javax.servlet.http.HttpSession;

import static cz.memsource.assignment.utils.Const.SESSION_ATTRIBUTE_OWNER_ID;
import static cz.memsource.assignment.utils.Const.SESSION_ATTRIBUTE_TOKEN;

public abstract class BaseController {

    protected void saveUserIntoSession(HttpSession session, MemsourceLoginResponse memsourceLoginResponse) {
        session.setAttribute(SESSION_ATTRIBUTE_TOKEN, memsourceLoginResponse.getToken());
        session.setAttribute(SESSION_ATTRIBUTE_OWNER_ID, memsourceLoginResponse.getUser().getId());
    }

    protected boolean isUserLogged(HttpSession session) {
        return session.getAttribute(SESSION_ATTRIBUTE_TOKEN) != null;
    }

    protected String getToken(HttpSession session) {
        return (String) session.getAttribute(SESSION_ATTRIBUTE_TOKEN);
    }

    protected String getOwnerId(HttpSession session) {
        return (String) session.getAttribute(SESSION_ATTRIBUTE_OWNER_ID);
    }
}
