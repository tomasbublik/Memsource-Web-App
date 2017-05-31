package cz.memsource.assignment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.memsource.assignment.api.MemsourceLoginResponse;
import cz.memsource.assignment.api.MemsourceProjectResponse;
import cz.memsource.assignment.exceptions.ValidationException;
import cz.memsource.assignment.model.ProjectResponseModel;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JsonConverterTest {

    private static final String ANY_USERNAME = "userName";
    private static final String ANY_PASSWORD = "password";
    private static final String NON_ZERO_CODE = "1";
    private static final String EMPTY_PROJECTS_RESPONSE = "{\"page\":0,\"totalCount\":0,\"projects\":[]}";
    private static final String ONE_PROJECT_RESPONSE = "{\n" +
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
        assertThat(generatedJson).isEqualTo("{\"ownerId\":\"" + clientId + "\"}");
    }

    @Test
    public void shouldThrowValidationExceptionWhenUsernameIsEmpty() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> JsonConverter.createLoginJson("", ANY_PASSWORD));
    }

    @Test
    public void shouldThrowValidationExceptionWhenClientIdIsEmpty() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> JsonConverter.createOwnerIdJson(""));
    }

    @Test
    public void shouldThrowValidationExceptionWhenClientIdIsNull() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> JsonConverter.createOwnerIdJson(null));
    }

    @Test
    public void shouldThrowValidationExceptionWhenPasswordIsEmpty() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> JsonConverter.createLoginJson(ANY_USERNAME, ""));
    }

    @Test
    public void shouldThrowValidationExceptionWhenUsernameIsNull() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> JsonConverter.createLoginJson(null, ANY_PASSWORD));
    }

    @Test
    public void shouldThrowValidationExceptionWhenPasswordIsNull() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> JsonConverter.createLoginJson(ANY_USERNAME, null));
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
        MemsourceProjectResponse memsourceProjectResponse = JsonConverter.createProjectResponse(EMPTY_PROJECTS_RESPONSE);

        assertThat(memsourceProjectResponse).isNotNull();
        assertThat(memsourceProjectResponse.getPage()).isNotNull();
        assertThat(memsourceProjectResponse.getTotalCount()).isNotEmpty();
    }

    @Test
    public void shouldCreateProjectResponseModelWhenCorrespondingStringIsGivenButProjectListIsEmpty() throws IOException, ValidationException {
        ProjectResponseModel projectResponseModel = JsonConverter.createProjectResponseModel(EMPTY_PROJECTS_RESPONSE);

        assertThat(projectResponseModel).isNotNull();
        assertThat(projectResponseModel.isSuccess()).isFalse();
        assertThat(projectResponseModel.getReason()).isEqualTo("Projects list is empty: {\"page\":0,\"totalCount\":0,\"projects\":[]}");
    }

    @Test
    public void shouldCreateProjectResponseModelWhenCorrespondingStringIsGiven() throws IOException, ValidationException {
        ProjectResponseModel projectResponseModel = JsonConverter.createProjectResponseModel(ONE_PROJECT_RESPONSE);

        assertThat(projectResponseModel).isNotNull();
        assertThat(projectResponseModel.isSuccess()).isTrue();
        assertThat(projectResponseModel.getReason()).isNull();
        assertThat(projectResponseModel.getProjectModels().size()).isEqualTo(1);
        assertThat(projectResponseModel.getProjectModels().get(0).getName()).isEqualTo("Nejlepší projekt na světě");
        assertThat(projectResponseModel.getProjectModels().get(0).getSourceLanguage()).isEqualTo("cs");
        assertThat(projectResponseModel.getProjectModels().get(0).getTargetLanguage()).isEqualTo("[la, li, ln, lo][la, li, ln, lo][la, li, ln, lo][la, li, ln, lo]");
        assertThat(projectResponseModel.getProjectModels().get(0).getStatus()).isEqualTo("NEW");
    }
}
