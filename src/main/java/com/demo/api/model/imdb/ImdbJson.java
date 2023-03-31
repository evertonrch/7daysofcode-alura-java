package com.demo.api.model.imdb;

public class ImdbJson {

    private Movie[] items;
    private String errorMessage;

    public Movie[] getItems() {
        return items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
