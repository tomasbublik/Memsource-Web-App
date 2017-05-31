package cz.memsource.assignment.controller;

import cz.memsource.assignment.service.MemsourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static cz.memsource.assignment.utils.Const.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProjectsControllerTest {

    private static final String USER_NOT_LOGGED_RESPONSE = "{\"projectModels\":null,\"reason\":\"User is not logged in\",\"success\":false}";
    private static final String JSON_ERROR_RESPONSE = "{\"projectModels\":null,\"reason\":\"JSON parsing error occurred\",\"success\":false}";
    private static final String FAKE_TOKEN = "1234567890qwertzuiopasdfghjklxcvbnm";
    private static final String FAKE_OWNER_ID = "222333";
    private static final String PROJECTS_RESPONSE = "{\n" +
            "  \"page\": 0,\n" +
            "  \"totalCount\": 1,\n" +
            "  \"projects\": [\n" +
            "    {\n" +
            "      \"uid\": \"0WukBNFfM6N3oa8LOjI642\",\n" +
            "      \"innerId\": 1,\n" +
            "      \"id\": 10244034,\n" +
            "      \"name\": \"Nejlepší projekt na světě\",\n" +
            "      \"dateCreated\": \"2017-05-30T08:46:34+0000\",\n" +
            "      \"domain\": null,\n" +
            "      \"subDomain\": null,\n" +
            "      \"owner\": {\n" +
            "        \"id\": 202950,\n" +
            "        \"firstName\": \"Tomáš\",\n" +
            "        \"lastName\": \"Bublík\",\n" +
            "        \"userName\": \"tomas.bublik@gmail.com\",\n" +
            "        \"email\": \"tomas.bublik@gmail.com\",\n" +
            "        \"role\": \"ADMIN\",\n" +
            "        \"timezone\": \"Europe/London\",\n" +
            "        \"active\": true,\n" +
            "        \"deleted\": false,\n" +
            "        \"terminologist\": false,\n" +
            "        \"dateCreated\": \"2017-05-23T08:54:46+0000\"\n" +
            "      },\n" +
            "      \"sourceLang\": \"cs\",\n" +
            "      \"targetLangs\": [\n" +
            "        \"la\",\n" +
            "        \"li\",\n" +
            "        \"ln\",\n" +
            "        \"lo\"\n" +
            "      ],\n" +
            "      \"shared\": false,\n" +
            "      \"progress\": {\n" +
            "        \"totalCount\": 0,\n" +
            "        \"finishedCount\": 0,\n" +
            "        \"overdueCount\": 0\n" +
            "      },\n" +
            "      \"client\": null,\n" +
            "      \"costCenter\": null,\n" +
            "      \"businessUnit\": null,\n" +
            "      \"dateDue\": \"2017-05-29T23:00:00+0000\",\n" +
            "      \"status\": \"NEW\",\n" +
            "      \"purchaseOrder\": \"\",\n" +
            "      \"jobBoard\": false,\n" +
            "      \"note\": \"\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private static final String RESPONSE_TO_BE_SHOWN = "{\"projectModels\":[{\"name\":\"Nejlepší projekt na světě\",\"sourceLanguage\":\"cs\",\"targetLanguage\":\"[la, li, ln, lo][la, li, ln, lo][la, li, ln, lo][la, li, ln, lo]\",\"status\":\"NEW\"}],\"reason\":null,\"success\":true}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemsourceService memsourceService;

    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        session = new MockHttpSession();
        session.setAttribute(SESSION_ATTRIBUTE_TOKEN, FAKE_TOKEN);
        session.setAttribute(SESSION_ATTRIBUTE_OWNER_ID, FAKE_OWNER_ID);
    }

    @Test
    public void isUserNotLoggedExpectedWhenNoUsernameAndPasswordIsProvided() throws Exception {
        mvc.perform(get(ENDPOINT_PROJECTS_MAPPING).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(USER_NOT_LOGGED_RESPONSE)));
    }

    @Test
    public void isParsingErrorReturnedWhenUserIsLoggedButNoResponseIsProvided() throws Exception {
        given(this.memsourceService.doPostRequest(eq(MEMSOURCE_PROJECTS_URL), anyString(), anyMap())).willReturn("");

        mvc.perform(get(ENDPOINT_PROJECTS_MAPPING).session(session).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(JSON_ERROR_RESPONSE)));
    }

    @Test
    public void isProjectListAcceptedWhenCorrectResponseIsProvided() throws Exception {
        given(this.memsourceService.doPostRequest(eq(MEMSOURCE_PROJECTS_URL), anyString(), anyMap())).willReturn(PROJECTS_RESPONSE);

        mvc.perform(get(ENDPOINT_PROJECTS_MAPPING).session(session).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(RESPONSE_TO_BE_SHOWN)));
    }


}
