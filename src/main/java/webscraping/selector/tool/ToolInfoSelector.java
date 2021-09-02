package webscraping.selector.tool;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.tool.ToolInfo;
import webscraping.util.Converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ToolInfoSelector {
    private ToolInfoSelector() {
    }

    public static ToolInfo getInfoTool(Document doc) throws IOException {
        ToolInfo toolInfo = new ToolInfo();

        //description
        Elements descElements = doc.select(".mw-parser-output > p");
        if (!descElements.isEmpty()) {
            if (!descElements.get(0).text().equals("")) {
                toolInfo.setDescription(descElements.get(0).text().trim());
            } else if (!descElements.get(1).text().equals("")) {
                toolInfo.setDescription(descElements.get(1).text().trim());
            } else {
                toolInfo.setDescription(descElements.get(2).text().trim());
            }
            if (toolInfo.getDescription().contains("[")) {
                toolInfo.setDescription(toolInfo.getDescription().replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            }
        }

        //image
        Elements imageElements = doc.select(".mw-parser-output .image");
        if (!imageElements.isEmpty()) {
            String urlImage = imageElements.attr("href");
            toolInfo.setImage(Converters.getBase64Image(urlImage.substring(0, urlImage.indexOf("/revision")) +
                    "/revision" +
                    "/latest/scale-to-width-down/300"));
        }

        //wielder
        Elements wielderElements = doc.select("h2:containsOwn(Wielder)");
        if (!wielderElements.isEmpty()) {
            List<String> wielders = new ArrayList<>();
            for (Element wielderElmt : wielderElements.first().parent().select("div div ul li a")) {
                String wielder = wielderElmt.text().trim();
                if (wielder.contains("(")) {
                    wielders.add(wielder.substring(0, wielder.indexOf(" (")).trim());

                } else {
                    wielders.add(wielder);
                }
            }
            toolInfo.setWielder(wielders);
        }

        log.info("Tool info base getted.");
        return toolInfo;
    }
}
