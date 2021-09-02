package webscraping.selector.tool;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import webscraping.model.tool.ToolName;

@Slf4j
public class ToolNameSelector {
    private ToolNameSelector() {
    }

    public static ToolName getToolName(Document doc) {
        //tool name
        ToolName toolName = new ToolName();
        Elements nameElements = doc.select("h2.pi-item.pi-item-spacing.pi-title");

        if (!nameElements.isEmpty()) {
            String english = nameElements.get(0).text();
            if (english.contains("[")) {
                String englishBracket = english.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim();
                if (englishBracket.contains("edit")) {
                    toolName.setEnglish(englishBracket.substring(0, englishBracket.lastIndexOf("edit")));
                } else {
                    toolName.setEnglish(english);
                }
            } else if (english.contains("edit")) {
                toolName.setEnglish(english.substring(0, english.lastIndexOf("edit")));
            } else {
                toolName.setEnglish(english);
            }
            toolName.setKanji(nameElements.get(1).select("rb").text());

            String romaji = nameElements.get(1).select("dfn").text();
            toolName.setRomaji(romaji.substring(1, romaji.length() - 1));
        }

        Elements otherNamesElements = doc.select("div[data-source=\"other names\"]");
        if (!otherNamesElements.isEmpty()) {
            String otherName = otherNamesElements.select("div div").text();
            if (otherName.contains("[")) {
                toolName.setOther(otherName.replaceAll("[0-9]", "").replace("[", "").replace("]", "").trim());
            } else {
                toolName.setOther(otherName);
            }

        }
        log.info("Tool name getted.");
        return toolName;
    }

}
