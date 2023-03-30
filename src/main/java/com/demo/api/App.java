package com.demo.api;

import com.demo.api.model.ImdbJson;
import com.demo.api.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    private static final String keyValue = "k_3fz2d7wz";

    public static void main(String[] args) {
        String json = null;
        try {
            json = getResponseBody(getRequest(getUri(keyValue)));
        } catch (URISyntaxException e) {
            System.err.println("malformed uri: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.err.println("No body: " + e.getMessage());
        }

        ObjectMapper mapper = new ObjectMapper();
        ImdbJson imdbJson = null;
        try {
            imdbJson = mapper.readValue(json, ImdbJson.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<String> titles = parseTitles(imdbJson);
        List<String> urlImages = parseUrlImages(imdbJson);


//        JsonNode jsonNode = jsonNode(mapper, json);
//        displayPrettierJson(jsonNode);
    }

    private static List<String> parseUrlImages(ImdbJson imdbJson) {
        List<Movie> listItems = Arrays.asList(imdbJson.getItems());
        return listItems.stream().map(Movie::getImage).collect(Collectors.toList());
    }

    private static List<String> parseTitles(ImdbJson imdbJson) {
        return Arrays.stream(imdbJson.getItems())
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }

    private static JsonNode jsonNode(ObjectMapper mapper, String json) {
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to convert json: " + e.getMessage());
        }
        return jsonNode;
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

    private static URI getUri(String keyValue) throws URISyntaxException {
        String full = "https://imdb-api.com/en/API/Top250Movies/" + keyValue;
        return new URI(full);
    }
}
