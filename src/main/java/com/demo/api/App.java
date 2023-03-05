package com.demo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    private static final String keyValue = null;
    public static void main(String[] args) {
        String body = null;
        try {
            body = getResponseBody(getRequest(fullUri(keyValue)));
        } catch (URISyntaxException e) {
            System.err.println("malformed uri: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.err.println("No body: " + e.getMessage());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(body);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to convert json: " + e.getMessage());
        }

        displayPrettierJson(jsonNode);
    }

    private static void displayPrettierJson(JsonNode jsonNode) {
        if (jsonNode == null)
            throw new NullPointerException("Json is null");

        System.out.println(jsonNode.toPrettyString());
    }

    private static String getResponseBody(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static HttpRequest getRequest(URI uri) {
        return HttpRequest
                .newBuilder(uri)
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    private static URI fullUri(String keyValue) throws URISyntaxException {
        String full = "https://imdb-api.com/en/API/Top250Movies/" + keyValue;
        return new URI(full);
    }
}
