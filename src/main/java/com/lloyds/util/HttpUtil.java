package com.lloyds.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HttpUtil {
    private static HttpClient client = HttpClient.newHttpClient();

    public static String sendGetRequest(String URL, Map<String, String> headers, Map<String, String> urlVariableMap, String accessToken) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(URL + urlVariableBuilder(urlVariableMap)));
        requestBuilder.header("accept", "application/json");
        requestBuilder.header("Authorization", "Bearer " + accessToken);
        if (headers != null) {
            for (String key: headers.keySet()) {
                requestBuilder.header(key, headers.get(key));
            }
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = null;
        try {
            log.error("sending request, request parameters: {}", request.headers().toString());
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception(response.body());
            }
        }  catch (IOException | InterruptedException e) {
            log.error("Sending get request failed, request URL: {}, error msg: {} ", URL, e);
        } catch (Exception e) {
            log.error("Sending get request failed, request URL: {}, error msg: {} ", URL, e);
            throw new Exception(e.getMessage());
        }
        return response.body();
    }


    private static String urlVariableBuilder(Map<String, String> urlVariableMap) {
        if (urlVariableMap == null || urlVariableMap.size() == 0) {
            return "";
        }
        return urlVariableMap.keySet().stream()
                .map(k -> k + "=" + encodeValue(urlVariableMap.get(k)))
                .collect(Collectors.joining("&", "?", ""));
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            log.error("encoding url variables error, value is {}", value);
        }
        return null;
    }

}
