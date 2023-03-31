package com.demo.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public interface APIClient {

    String getBody() throws IOException, InterruptedException, URISyntaxException;
    URI getUri();

}
