package com.demo.api.utils;

import com.demo.api.model.Content;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface JsonParse {

    Optional<List<Content>> parse() throws JsonProcessingException;

}

