package cz.memsource.assignment.controller;

import cz.memsource.assignment.exceptions.ValidationException;
import cz.memsource.assignment.model.ProjectResponseModel;
import cz.memsource.assignment.service.MemsourceService;
import cz.memsource.assignment.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static cz.memsource.assignment.utils.Const.*;

@Controller
@RequestMapping(ENDPOINT_PROJECTS_MAPPING)
public class ProjectsController extends BaseController {

    private MemsourceService memsourceService;

    @Autowired
    public ProjectsController(MemsourceService memsourceService) {
        this.memsourceService = memsourceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ProjectResponseModel getProjects(HttpSession session) {

        if (!isUserLogged(session)) {
            //TODO redirect to login or error message?
            return new ProjectResponseModel("User is not logged in");
        }

        String response;
        try {
            response = memsourceService.doPostRequest(MEMSOURCE_PROJECTS_URL, JsonConverter.createOwnerIdJson(getOwnerId(session)), createParametersMap(session));
            return JsonConverter.createProjectResponseModel(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new ProjectResponseModel("IO error occurred");
        } catch (ValidationException e) {
            e.printStackTrace();
            return new ProjectResponseModel("JSON parsing error occurred");
        }
    }

    private HashMap createParametersMap(HttpSession session) {
        HashMap parametersMap = new HashMap();
        parametersMap.put(MEMSOURCE_API_TOKEN, getToken(session));
        return parametersMap;
    }
}
