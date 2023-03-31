package com.demo.api.view;

import com.demo.api.model.Movie;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies) {
            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>");
            movies.forEach(movie -> {
                sb.append("<section style=\"display:flex;flex-direction:column;align-items:center\">");
                sb.append("<h3 style=\"text-align:center;\">").append(movie.getTitle()).append("</h3>");
                sb.append("<img width=\"200\" height=\"200\" src=\"").append(movie.getImage()).append("\" />");
                sb.append("<div style=\"display:flex; gap: 50px;\">");
                sb.append("<strong>").append(movie.getImDbRating()).append("</strong>");
                sb.append("<strong>").append(movie.getYear()).append("</strong>");
                sb.append("</div>");
                sb.append("</section>");
            });
            sb.append("</body></html>");
            writer.write(sb.toString());
            writer.flush();
            writer.close();
    }

}
