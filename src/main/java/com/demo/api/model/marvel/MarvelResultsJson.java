package com.demo.api.model.marvel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResultsJson {

    private int total;

    @JsonProperty(value = "results")
    private MarvelSerie[] marvelSeries;

    public int getTotal() {
        return total;
    }

    public MarvelSerie[] getMarvelSeries() {
        return marvelSeries;
    }
}
