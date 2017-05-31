package cz.memsource.assignment.controller;

import cz.memsource.assignment.service.MemsourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.HashMap;

import static cz.memsource.assignment.utils.Const.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerTest {

    private static final String USER_NOT_LOGGED_RESPONSE = "{\"success\":false,\"reason\":\"User is not logged in, username or password cannot be empty\"}";
    private static final String PARSING_ERROR_RESPONSE = "{\"success\":false,\"reason\":\"JSON parsing error occurred\"}";
    private static final String USER_LOGIN_SUCCESS_RESPONSE = "{\"success\":true,\"reason\":\"\"}";
    private static final String USER_LOGIN_IO_ERROR_RESPONSE = "{\"success\":false,\"reason\":\"IO error occurred\"}";
    private static final String FAKE_PASSWORD = "bbb";
    private static final String FAKE_USERNAME = "aaa";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemsourceService memsourceService;

    @Test
    public void isUserNotLoggedExpectedWhenNoUsernameAndPasswordIsProvided() throws Exception {
        mvc.perform(get(ENDPOINT_LOGIN_MAPPING).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(USER_NOT_LOGGED_RESPONSE)));
    }

    @Test
    public void isParsingErrorOccurringWhenWrongUsernameAndPasswordIsProvided() throws Exception {
        mvc.perform(get(ENDPOINT_LOGIN_MAPPING).param("name", "aaa")
                .param("password", "bbb").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(PARSING_ERROR_RESPONSE)));
    }

    @Test
    public void isUserLoggedInWhenProperUsernameAndPasswordIsProvided() throws Exception {
        setupResponse(true);

        mvc.perform(get(ENDPOINT_LOGIN_MAPPING).param("name", FAKE_USERNAME)
                .param("password", FAKE_PASSWORD).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(USER_LOGIN_SUCCESS_RESPONSE)));
    }

    @Test
    public void isErrorReturnedWhenWrongJsonIsProvided() throws Exception {
        setupResponse(false);

        mvc.perform(get(ENDPOINT_LOGIN_MAPPING).param("name", FAKE_USERNAME)
                .param("password", FAKE_PASSWORD).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(USER_LOGIN_IO_ERROR_RESPONSE)));
    }

    private void setupResponse(boolean positive) throws IOException {
        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put(MEMSOURCE_API_USER_NAME, FAKE_USERNAME);
        parameters.put(MEMSOURCE_API_PASSWORD, FAKE_PASSWORD);
        String loginResponseJson;
        if (positive) {
            loginResponseJson = "{\"user\":{\"id\":202950,\"firstName\":\"Tom\\u00e1\\u0161\",\"lastName\":\"Bubl\\u00edk\",\"userName\":\"tomas.bublik@gmail.com\",\"email\":\"tomas.bublik@gmail.com\",\"role\":\"ADMIN\",\"timezone\":\"Europe/London\",\"active\":true,\"deleted\":false,\"terminologist\":false,\"dateCreated\":\"2017-05-23T08:54:46+0000\"},\"token\":\"0jDBXpHMhFULVqCvxiYg1j7SSKZGHX7vyJE01w5EBIBPI6D9EfaEyGFRAyJGEOaDd\",\"expires\":\"2017-05-27T05:59:26+0000\"}";
        } else {
            loginResponseJson = "eee";
        }
        given(this.memsourceService.doGetRequest(MEMSOURCE_LOGIN_URL, parameters)).willReturn(loginResponseJson);
    }
}
