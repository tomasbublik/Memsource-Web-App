package cz.memsource.assignment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.memsource.assignment.api.MemsourceProjectResponse;
import cz.memsource.assignment.exceptions.ValidationException;
import cz.memsource.assignment.api.MemsourceLoginResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JsonConverterTest {

    public static final String ANY_USERNAME = "userName";
    public static final String ANY_PASSWORD = "password";
    public static final String NON_ZERO_CODE = "1";

    @Test
    public void shouldCreateLoginJsonWhenUsernameAndPasswordIsGiven() throws ValidationException, JsonProcessingException {
        String generatedJson = JsonConverter.createLoginJson(ANY_USERNAME, ANY_PASSWORD);

        assertThat(generatedJson).isNotNull();
        assertThat(generatedJson).isEqualTo("{\"userName\":\"" + ANY_USERNAME + "\",\"password\":\"" + ANY_PASSWORD + "\",\"code\":\"0\"}");
    }

    @Test
    public void shouldCreateLoginJsonWhenDifferentUsernameAndPasswordIsGiven() throws ValidationException, JsonProcessingException {
        final String differentName = "differentName";
        String generatedJson = JsonConverter.createLoginJson(differentName, ANY_PASSWORD);

        assertThat(generatedJson).isNotNull();
        assertThat(generatedJson).isEqualTo("{\"userName\":\"" + differentName + "\",\"password\":\"" + ANY_PASSWORD + "\",\"code\":\"0\"}");
    }

    @Test
    public void shouldContainCodeWhenCertainCodeIsProvided() throws ValidationException, JsonProcessingException {
        final String differentName = "differentName";
        String generatedJson = JsonConverter.createLoginJson(differentName, ANY_PASSWORD, NON_ZERO_CODE);

        assertThat(generatedJson).isNotNull();
        assertThat(generatedJson).isEqualTo("{\"userName\":\"" + differentName + "\",\"password\":\"" + ANY_PASSWORD + "\",\"code\":\"" + NON_ZERO_CODE + "\"}");
    }

    @Test
    public void shouldContainClientIdWhenCertainIdIsProvided() throws ValidationException, JsonProcessingException {
        final String clientId = "20090";
        String generatedJson = JsonConverter.createOwnerIdJson(clientId);

        assertThat(generatedJson).isNotNull();
        assertThat(generatedJson).isEqualTo("{\"ownerId\":\""+clientId+"\"}");
    }

    @Test
    public void shouldThrowValidationExceptionWhenUsernameIsEmpty() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> {
            JsonConverter.createLoginJson("", ANY_PASSWORD);
        });
    }

    @Test
    public void shouldThrowValidationExceptionWhenClientIdIsEmpty() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> {
            JsonConverter.createOwnerIdJson("");
        });
    }

    @Test
    public void shouldThrowValidationExceptionWhenClientIdIsNull() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> {
            JsonConverter.createOwnerIdJson(null);
        });
    }

    @Test
    public void shouldThrowValidationExceptionWhenPasswordIsEmpty() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> {
            JsonConverter.createLoginJson(ANY_USERNAME, "");
        });
    }

    @Test
    public void shouldThrowValidationExceptionWhenUsernameIsNull() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> {
            JsonConverter.createLoginJson(null, ANY_PASSWORD);
        });
    }

    @Test
    public void shouldThrowValidationExceptionWhenPasswordIsNull() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> {
            JsonConverter.createLoginJson(ANY_USERNAME, null);
        });
    }

    @Test
    public void shouldCreateLoginResponseObjectWhenCorrespondingStringIsGiven() throws IOException, ValidationException {
      String loginResponseJson = "{\"user\":{\"id\":202950,\"firstName\":\"Tom\\u00e1\\u0161\",\"lastName\":\"Bubl\\u00edk\",\"userName\":\"tomas.bublik@gmail.com\",\"email\":\"tomas.bublik@gmail.com\",\"role\":\"ADMIN\",\"timezone\":\"Europe/London\",\"active\":true,\"deleted\":false,\"terminologist\":false,\"dateCreated\":\"2017-05-23T08:54:46+0000\"},\"token\":\"0jDBXpHMhFULVqCvxiYg1j7SSKZGHX7vyJE01w5EBIBPI6D9EfaEyGFRAyJGEOaDd\",\"expires\":\"2017-05-27T05:59:26+0000\"}";

      MemsourceLoginResponse memsourceLoginResponse = JsonConverter.createLoginResponse(loginResponseJson);

      assertThat(memsourceLoginResponse).isNotNull();
      assertThat(memsourceLoginResponse.getUser()).isNotNull();
      assertThat(memsourceLoginResponse.getToken()).isNotEmpty();
    }

    @Test
    public void shouldCreateProjectResponseObjectWhenCorrespondingStringIsGivenButProjectListIsEmpty() throws IOException, ValidationException {
      String projectResponseJson = "{\"page\":0,\"totalCount\":0,\"projects\":[]}";

      MemsourceProjectResponse memsourceProjectResponse = JsonConverter.createProjectResponse(projectResponseJson);

      assertThat(memsourceProjectResponse).isNotNull();
      assertThat(memsourceProjectResponse.getPage()).isNotNull();
      assertThat(memsourceProjectResponse.getTotalCount()).isNotEmpty();
    }
}
