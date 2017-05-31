package cz.memsource.assignment.controller;

import cz.memsource.assignment.exceptions.ValidationException;
import cz.memsource.assignment.model.LoginResponseModel;
import cz.memsource.assignment.api.MemsourceLoginResponse;
import cz.memsource.assignment.service.MemsourceService;
import cz.memsource.assignment.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static cz.memsource.assignment.utils.Const.*;
import static cz.memsource.assignment.utils.ValidationUtils.nameOrPasswordEmpty;

@Controller
@RequestMapping(ENDPOINT_LOGIN_MAPPING)
public class LoginController extends BaseController {

    private MemsourceService memsourceService;

    @Autowired
    public LoginController(MemsourceService memsourceService) {
        this.memsourceService = memsourceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    LoginResponseModel login(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "password", required = false, defaultValue = "") String password, HttpSession session) {

        if (isUserLogged(session)) {
            return new LoginResponseModel(true, "Already logged in");
        }

        if (nameOrPasswordEmpty(name, password)) {
            return new LoginResponseModel(false, "User is not logged in, username or password cannot be empty");
        }

        String response;
        try {
            response = memsourceService.doGetRequest(MEMSOURCE_LOGIN_URL, createParametersMap(name, password));
            MemsourceLoginResponse memsourceLoginResponse = JsonConverter.createLoginResponse(response);
            saveUserIntoSession(session, memsourceLoginResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return new LoginResponseModel(false, "IO error occurred");
        } catch (ValidationException e) {
            e.printStackTrace();
            return new LoginResponseModel(false, "JSON parsing error occurred");
        }

        return new LoginResponseModel();
    }

    private HashMap createParametersMap(String name, String password) {
        HashMap parametersMap = new HashMap();
        parametersMap.put(MEMSOURCE_API_USER_NAME, name);
        parametersMap.put(MEMSOURCE_API_PASSWORD, password);
        return parametersMap;
    }
}
