package com.demo.api.utils;

import com.demo.api.model.Content;
import com.demo.api.model.marvel.MarvelResponseJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class MarvelJsonParse implements JsonParse {

    private final String json;
    private final ObjectMapper mapper = new ObjectMapper();

    public MarvelJsonParse(String json) {
        this.json = json;
    }

    @Override
    public Optional<List<Content>> parse() throws JsonProcessingException {
        MarvelResponseJson marvelResponseJson = new ObjectMapper()
                .readValue(json, MarvelResponseJson.class);
        return Optional.ofNullable(Arrays
                .asList(marvelResponseJson
                        .getResultsJson()
                        .getMarvelSeries()));
    }
}
