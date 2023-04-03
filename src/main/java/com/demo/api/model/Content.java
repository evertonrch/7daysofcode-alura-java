package com.demo.api.model;

public interface Content extends Comparable<Content> {

    String getTitle();
    String getImage();
    String getRating();
    String getYear();
}
