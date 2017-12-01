package com.github.yannismarkou.gservices;

import com.github.yannismarkou.gui.ImageText;
import com.github.yannismarkou.resources.Resources;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

import static com.github.yannismarkou.resources.Resources.GVISION_TARGET_URL;
import static com.github.yannismarkou.resources.Resources.gVisionJSON_pt1;
import static com.github.yannismarkou.resources.Resources.gVisionJSON_pt3;

/**
 *
 * @author yannis
 */
public class GCloudVision {

    public ImageText getImageText(String imageURI) throws ProtocolException, IOException {

        String API_KEY = "key=" + Resources.gAPIKey;

        URL serverUrl;
        URLConnection urlConnection;
        HttpURLConnection httpConnection;

        serverUrl = new URL(GVISION_TARGET_URL + API_KEY);
        urlConnection = serverUrl.openConnection();
        httpConnection = (HttpURLConnection) urlConnection;

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setDoOutput(true);

        byte[] imageData64 = imageToBase64(imageURI);
        String imageBase64 = new String(imageData64);

        String json = gVisionJSON_pt1 + imageBase64 + gVisionJSON_pt3;

        try (BufferedWriter httpRequestBodyWriter = new BufferedWriter(
                new OutputStreamWriter(httpConnection.getOutputStream()))) {

            httpRequestBodyWriter.write(json);
        }

        if (httpConnection.getInputStream() == null) {
            return null;
        }

        try (Scanner httpResponseScanner = new Scanner(httpConnection.getInputStream())) {
            String response = "";
            while (httpResponseScanner.hasNext()) {
                String line = httpResponseScanner.nextLine();
                response += line;
            }

            JsonParser parser = new JsonParser();

            JsonElement element = parser.parse(response);

            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("error") == null) {
                    String extractedLocale = obj.get("responses").getAsJsonArray().
                            get(0).getAsJsonObject().
                            get("textAnnotations").getAsJsonArray().
                            get(0).getAsJsonObject().
                            get("locale").getAsString();

                    String extractedText = obj.get("responses").getAsJsonArray().
                            get(0).getAsJsonObject().
                            get("textAnnotations").getAsJsonArray().
                            get(0).getAsJsonObject().
                            get("description").getAsString().trim().split("\\r?\\n")[0];

                    return new ImageText(extractedLocale, extractedText);
                }
            }
        }

        return null;
    }

    //encode image to Base64 String
    public byte[] imageToBase64(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        FileInputStream fis = new FileInputStream(imageFile);
        byte byteArray[] = new byte[(int) imageFile.length()];
        fis.read(byteArray);
        byte[] imageData = Base64.encodeBase64(byteArray);

        return imageData;
    }
}
