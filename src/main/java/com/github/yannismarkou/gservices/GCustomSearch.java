package com.github.yannismarkou.gservices;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

import static com.github.yannismarkou.resources.Resources.GSEARCH_BASE_URL;
import static com.github.yannismarkou.resources.Resources.GSEARCH_BASE_URL_3;
import static com.github.yannismarkou.resources.Resources.GSEARCH_BASE_URL_5;

/**
 *
 * @author yannis
 */
public class GCustomSearch {

    private final String key;

    public GCustomSearch(String apiKey) {
        key = apiKey;
    }

    public String search(String locale, String searchText) {
        StringBuilder result = new StringBuilder();
        try {
            String encodedSText = URLEncoder.encode(searchText, "UTF-8");
            String searchString = GSEARCH_BASE_URL + key + GSEARCH_BASE_URL_3
                    + locale + GSEARCH_BASE_URL_5 + encodedSText;

            URL searchURL = new URL(searchString);

            HttpsURLConnection conn = (HttpsURLConnection) searchURL.openConnection();
            InputStream stream;
            if (conn.getResponseCode() == 200) //success
            {
                stream = conn.getInputStream();
            } else {
                stream = conn.getErrorStream();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JsonParser parser = new JsonParser();

            JsonElement element = parser.parse(result.toString());

            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("error") == null) {
                    try {
                        String correctedText = obj.get("spelling").getAsJsonObject().
                            get("correctedQuery").getAsString();
                        return correctedText;
                    } catch(NullPointerException e) {
                        return null;
                    }
                }
            }

            if (conn.getResponseCode() != 200) {
                System.err.println(result);
            }

        } catch (IOException | JsonSyntaxException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
}
