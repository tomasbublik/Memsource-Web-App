package cz.memsource.assignment.service;

import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static cz.memsource.assignment.utils.Const.JSON;

@Service
public class MemsourceServiceImpl implements MemsourceService {

    private OkHttpClient memsourceClient = new OkHttpClient();

    public String doPostRequest(String urlAddress, String json, Map<String, String> parameters) throws IOException {
        String url = buildUrl(urlAddress, parameters);
        
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = memsourceClient.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public String doGetRequest(String urlAddress, Map<String, String> parameters) throws IOException {
        String url = buildUrl(urlAddress, parameters);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = memsourceClient.newCall(request).execute();
        return response.body().string();
    }

    private String buildUrl(String urlAddress, Map<String, String> parameters) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlAddress).newBuilder();
        addParameters(parameters, urlBuilder);
        return urlBuilder.build().toString();
    }

    private void addParameters(Map<String, String> parameters, HttpUrl.Builder urlBuilder) {
        if (parameters != null && !parameters.isEmpty()) {
            for (String key : parameters.keySet()) {
                urlBuilder.addQueryParameter(key, parameters.get(key));
            }
        }
    }
}
