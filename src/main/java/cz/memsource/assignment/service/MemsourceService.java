package cz.memsource.assignment.service;

import java.io.IOException;
import java.util.Map;

public interface MemsourceService {

    String doPostRequest(String urlAddress, String json, Map<String, String> parameters) throws IOException;

    String doGetRequest(String urlAddress, Map<String, String> parameters) throws IOException;
}
