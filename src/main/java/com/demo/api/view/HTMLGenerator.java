package com.demo.api.view;

import com.demo.api.model.Content;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<Content> contents) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        contents.forEach(content -> {
            sb.append("<section style=\"display:flex;flex-direction:column;align-items:center\">");
            sb.append("<h3 style=\"text-align:center;\">").append(content.getTitle()).append("</h3>");
            sb.append("<img width=\"200\" height=\"200\" src=\"").append(content.getImage()).append("\" />");
            sb.append("<div style=\"display:flex; gap: 50px;\">");
            sb.append("<strong>").append(content.getRating()).append("</strong>");
            sb.append("<strong>").append(content.getYear()).append("</strong>");
            sb.append("</div>");
            sb.append("</section>");
        });
        sb.append("</body></html>");
        writer.write(sb.toString());
        writer.flush();
        System.out.println("HTML Generated!");
    }

}
