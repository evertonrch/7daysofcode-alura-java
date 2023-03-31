package com.demo.api.model.marvel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelResponseJson {

    private int code;
    private String status;

    @JsonProperty(value = "data")
    private MarvelResultsJson resultsJsonJson;


    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public MarvelResultsJson getResultsJson() {
        return resultsJsonJson;
    }
}
