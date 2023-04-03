package com.demo.api;

import com.demo.api.client.APIClient;
import com.demo.api.client.ImdbApiClient;
import com.demo.api.client.MarvelApiClient;
import com.demo.api.model.Content;
import com.demo.api.utils.ImdbJsonParse;
import com.demo.api.utils.JsonParse;
import com.demo.api.utils.MarvelJsonParse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class BootstrapApp {

    private final ObjectMapper mapper = new ObjectMapper();

    // IMDB key
    private static final String keyValue = "";

    // Marvel API Keys
    private final static String timestamp = "";
    private final static String apiKey = "";
    private final static String hash = "";

    private APIClient client;
    private JsonParse jsonParse;
    private String jsonBody;

    public BootstrapApp(int option) throws IOException, InterruptedException, URISyntaxException {
        if(option <= 0 || option > 2)
            throw new IllegalArgumentException("Option is invalid");

        apiClient(option);
        jsonBody = client.getBody();
        System.out.println(jsonBody);

        checkTypeClient(client);
    }

    private void checkTypeClient(APIClient client) {
        if (client instanceof MarvelApiClient)
            this.jsonParse = new MarvelJsonParse(jsonBody);

        if (client instanceof ImdbApiClient)
            this.jsonParse = new ImdbJsonParse(jsonBody);
    }

    public List<Content> content() throws JsonProcessingException {
        Optional<List<Content>> parse = jsonParse.parse();
        return parse.orElseThrow();
    }

    private void apiClient(int option){
        if (option == 1)
            this.client = new MarvelApiClient(apiKey, hash, timestamp);

        if (option == 2)
            this.client = new ImdbApiClient(keyValue);
    }
}
