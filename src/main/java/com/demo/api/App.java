package com.demo.api;

import com.demo.api.model.Content;
import com.demo.api.view.HTMLGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        System.out.println("1 - Marvel Series API\n2 - IMDB Movies");
        int option = scanner.nextInt();

        List<Content> contents = new BootstrapApp(option).content();
        Collections.sort(contents);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("content.html");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        new HTMLGenerator(writer).generate(contents);
        writer.close();
    }
}
