package com.demo.api.model.marvel;

import com.demo.api.model.Content;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelSerie implements Content {

    private int id;
    private String title;


    private String image;

    @JsonProperty("startYear")
    private String year;
    private String rating;

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getImage() {
       return this.image;
    }

    @Override
    public String getRating() {
        return this.rating;
    }

    @Override
    public String getYear() {
        return this.year;
    }

    public int getId() {
        return id;
    }
}
