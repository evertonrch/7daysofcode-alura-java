package com.demo.api.utils;

import com.demo.api.model.imdb.ImdbJson;
import com.demo.api.model.imdb.Movie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseJsonProperties {

    public static List<String> parseUrlImages(ImdbJson imdbJson) {
        List<Movie> listItems = Arrays.asList(imdbJson.getItems());
        return listItems.stream().map(Movie::getImage).collect(Collectors.toList());
    }

    public static List<String> parseTitles(ImdbJson imdbJson) {
        return Arrays.stream(imdbJson.getItems())
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }
}
