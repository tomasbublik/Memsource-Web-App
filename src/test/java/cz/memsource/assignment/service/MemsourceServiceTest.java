package cz.memsource.assignment.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MemsourceServiceTest {

    public static final String ANY_URL = "/get/something";
    public static final String TEST_RESPONSE = "some response";
    private MemsourceServiceImpl classUnderTest;

    private MockWebServer server;

    @Before
    public void init() throws IOException {
        classUnderTest = new MemsourceServiceImpl();
        server = new MockWebServer();

        server.enqueue(new MockResponse().setBody(TEST_RESPONSE));

        server.start(8090);
    }

    @After
    public void cleanUp() throws IOException {
        server.shutdown();
    }

    @Test
    public void shouldReturnResponseWhenUrlIsProvided() throws IOException {
        String response = classUnderTest.doPostRequest(server.url(ANY_URL).toString(), "", new HashMap<String, String>());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(TEST_RESPONSE);
    }


    @Test
    public void shouldContinueWhenWrongJsonIsProvided() throws IOException {
        String response = classUnderTest.doPostRequest(server.url(ANY_URL).toString(), "123456789", new HashMap<String, String>());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(TEST_RESPONSE);
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNoJsonIsProvided() throws IOException {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            classUnderTest.doPostRequest(server.url(ANY_URL).toString(), null, new HashMap<String, String>());
        });
    }

    @Test
    public void shouldThrowErrorWhenUnexpectedUrlIsProvided() throws IOException {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            classUnderTest.doPostRequest(ANY_URL, "", new HashMap<String, String>());
        });
    }
}
