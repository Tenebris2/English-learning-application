package com.example.englishlearningappv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GoogleTranslate {
    private static final String ENG_LANG = "en";
    private static final String VIE_LANG = "vi";

    public static String translate(String input, String langFrom, String langTo) {
        try {
            String url = String.format(
                    "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s",
                    langFrom, langTo, URLEncoder.encode(input, "UTF-8"));
            URI uri = new URI(url);

            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(uri);

            HttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                Gson gson = new Gson();
                List<List<List<Object>>> jsonData = gson.fromJson(result, List.class);

                return (String) jsonData.get(0).get(0).get(0);
            } else {
                System.out.println("Error in connection. Status Code: " + statusCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
