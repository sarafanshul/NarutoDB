package webscraping.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public class JsoupConnection {
    private JsoupConnection() {
    }

    //character / jutsu / clan / kekkei genkai / team / village / country / tools
    public static Document connectionInfo(String name) {
        final String URL = "https://naruto.fandom.com/wiki/" + name;
        try {
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            log.error("Jsoup connection error.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Url not found", e);
        }
    }

    //kekkei genkai img icon connection
    public static Document connectionKekkeiIcon() {
        final String URL = "https://naruto.fandom.com/wiki/Kekkei_Genkai";
        try {
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            log.error("Jsoup kekkei icon img connection error.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Url not found", e);
        }
    }
}
