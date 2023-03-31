package com.demo.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MarvelApiClient implements APIClient {

    private final String apiKey;
    private final String hash;
    private final String timestamp;

    public MarvelApiClient(String apiKey, String hash, String timestamp) {
        this.apiKey = apiKey;
        this.hash = hash;
        this.timestamp = timestamp;
    }

    @Override
    public String getBody() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        return client.send(getRequest(), HttpResponse.BodyHandlers.ofString()).body();
    }

    private HttpResponse<String> getResponse() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        return client.send(getRequest(), HttpResponse.BodyHandlers.ofString());
    }

    private HttpRequest getRequest() {
        return HttpRequest.newBuilder()
                .uri(getUri())
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    @Override
    public URI getUri() {
        return URI.create("" +
                "https://gateway.marvel.com/v1/public/series?" +
                "ts=" + timestamp + "&apikey=" + apiKey + "&hash=" + hash
        );
    }

    @Override
    public String toString() {
        try {
            return "Status: " + getResponse().statusCode();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
