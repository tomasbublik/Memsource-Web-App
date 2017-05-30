package cz.memsource.assignment.utils;

import cz.memsource.assignment.exceptions.ValidationException;
import org.springframework.web.bind.annotation.RequestParam;

public class ValidationUtils {

    public static boolean nameOrPasswordEmpty(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "password", required = false, defaultValue = "") String password) {
        return name == null || name.isEmpty() || password == null || password.isEmpty();
    }

    public static void validateUsernameAndPassword(String userName, String password) throws ValidationException {
        validateParameter(userName, "Username cannot be empty");
        validateParameter(password, "Password cannot be empty");
    }

    public static void validateParameter(String parameter, String message) throws ValidationException {
        if (parameter == null || parameter.isEmpty()) {
            throw new ValidationException(message);
        }
    }
}
