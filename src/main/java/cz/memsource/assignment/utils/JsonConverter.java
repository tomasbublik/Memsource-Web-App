package cz.memsource.assignment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.memsource.assignment.api.MemsourceOwnerId;
import cz.memsource.assignment.api.MemsourceProjectResponse;
import cz.memsource.assignment.exceptions.ValidationException;
import cz.memsource.assignment.api.MemsourceLogin;
import cz.memsource.assignment.api.MemsourceLoginResponse;

import java.io.IOException;

import static cz.memsource.assignment.utils.ValidationUtils.validateParameter;
import static cz.memsource.assignment.utils.ValidationUtils.validateUsernameAndPassword;

public class JsonConverter {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String createLoginJson(String userName, String password, String code) throws ValidationException, JsonProcessingException {
        validateUsernameAndPassword(userName, password);

        MemsourceLogin memsourceLogin = new MemsourceLogin(userName, password, code);
        return mapper.writeValueAsString(memsourceLogin);
    }

    public static String createOwnerIdJson(String ownerId) throws ValidationException, JsonProcessingException {
        validateParameter(ownerId, "Owner id cannot be empty");

        MemsourceOwnerId memsourceOwnerId = new MemsourceOwnerId(ownerId);
        return mapper.writeValueAsString(memsourceOwnerId);
    }

    public static String createLoginJson(String userName, String password) throws ValidationException, JsonProcessingException {
        validateUsernameAndPassword(userName, password);

        MemsourceLogin memsourceLogin = new MemsourceLogin(userName, password);
        return mapper.writeValueAsString(memsourceLogin);
    }

    public static MemsourceLoginResponse createLoginResponse(String response) throws IOException, ValidationException {
        validateParameter(response, "Response JSON cannot be empty");
        return mapper.readValue(response, MemsourceLoginResponse.class);
    }

    public static MemsourceProjectResponse createProjectResponse(String response) throws IOException, ValidationException {
        validateParameter(response, "Response JSON cannot be empty");
        return mapper.readValue(response, MemsourceProjectResponse.class);
    }
}
