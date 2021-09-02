package webscraping.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Slf4j
public class Converters {
    private Converters() {
    }

    //convert object to json
    public static String convertObjectToJson(Object obj) {
        ObjectMapper json = new ObjectMapper();
        try {
            return json.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        } catch (Exception ex) {
            log.error("Error converting object to json");
        }
        return null;
    }

    //convert url image to base64
    public static String getBase64Image(String urlImage) throws IOException {
        URL url = new URL(urlImage);
        InputStream is = url.openStream();
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        return Base64.encodeBase64String(bytes);
    }
}
