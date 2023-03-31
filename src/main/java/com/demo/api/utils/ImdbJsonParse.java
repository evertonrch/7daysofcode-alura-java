package com.demo.api.utils;

import com.demo.api.model.Content;
import com.demo.api.model.imdb.ImdbJson;
import com.demo.api.model.imdb.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ImdbJsonParse implements JsonParse {

    private final String json;
    private final ObjectMapper mapper = new ObjectMapper();

    public ImdbJsonParse(String json) {
        this.json = json;
    }

    @Override
    public Optional<List<Content>> parse() {
        try {
            ImdbJson imdbJson = mapper.readValue(json, ImdbJson.class);
            Movie[] items = imdbJson.getItems();
            return Optional.ofNullable(Arrays.asList(items));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
