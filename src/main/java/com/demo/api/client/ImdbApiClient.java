package com.demo.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient implements APIClient {

    private String key;

    public ImdbApiClient(String key) {
        this.key = key;
    }

    @Override
    public String getBody() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(getRequest(), HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private HttpRequest getRequest() throws URISyntaxException {
        return HttpRequest
                .newBuilder(getUri())
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    @Override
    public URI getUri() {
        return URI.create("https://imdb-api.com/en/API/Top250Movies/" + key);
    }
}
