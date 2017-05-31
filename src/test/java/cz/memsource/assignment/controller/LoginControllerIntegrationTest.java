package cz.memsource.assignment.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static cz.memsource.assignment.utils.Const.ENDPOINT_LOGIN_MAPPING;
import static cz.memsource.assignment.utils.Const.HTTP_LOCALHOST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerIntegrationTest {

    private static final String USER_NOT_LOGGED_RESPONSE = "{success=false, reason=User is not logged in, username or password cannot be empty}";
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String localLoginUrl;

    @Before
    public void init() {
        localLoginUrl = HTTP_LOCALHOST + ":" + this.port + ENDPOINT_LOGIN_MAPPING;
    }

    @Test
    public void shouldReturn200WhenSendingRequestToLoginController() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(localLoginUrl, Map.class);

        assertThat(entity.getStatusCode()).isEqualTo(OK);
        assertThat(entity.getBody().toString()).isEqualTo(USER_NOT_LOGGED_RESPONSE);
    }

}
