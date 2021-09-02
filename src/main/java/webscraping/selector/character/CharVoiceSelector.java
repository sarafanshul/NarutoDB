package webscraping.selector.character;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import webscraping.model.character.CharVoice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharVoiceSelector {
    private CharVoiceSelector() {
    }

    public static CharVoice getInfoVoices(Document doc) {
        CharVoice charVoice = new CharVoice();

        //voice actors japanese
        List<String> japaneseVoices = new ArrayList<>();
        Elements japVoiceElements = doc.select("th:containsOwn(Japanese)");
        if (!japVoiceElements.isEmpty()) {
            for (Element element : japVoiceElements.first().parent().children().select(".extiw")) {
                japaneseVoices.add(element.text());
            }
            charVoice.setJapanese(japaneseVoices);
        }

        //voice actors english
        List<String> englishVoices = new ArrayList<>();
        Elements engVoiceElements = doc.select("th:containsOwn(English)");
        if (!engVoiceElements.isEmpty()) {
            for (Element element : engVoiceElements.first().parent().children().select(".extiw")) {
                englishVoices.add(element.text());
            }
            charVoice.setEnglish(englishVoices);
        }

        log.info("Voice info getted.");
        return charVoice;
    }
}
