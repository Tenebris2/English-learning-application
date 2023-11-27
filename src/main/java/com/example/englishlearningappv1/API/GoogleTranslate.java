package com.example.englishlearningappv1.API;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

public class GoogleTranslate implements APInterface {

    public static String translate(String input, String langFrom, String langTo) {
        try {
            String url = String.format(
                    "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s",
                    langFrom, langTo, URLEncoder.encode(input, "UTF-8"));
            System.out.println(URLEncoder.encode(input, "UTF-8"));
            URI uri = new URI(url);

            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(uri);

            HttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                Gson gson = new Gson();
                List<List<List<Object>>> jsonData = gson.fromJson(result, List.class);
                try {
                    String rep = (String) jsonData.get(0).get(0).get(0);
                    if (rep != null) {
                        System.out.println((String) jsonData.get(0).get(0).get(0));
                        return (String) jsonData.get(0).get(0).get(0);
                    }
                } catch (NullPointerException e) {
                    return "";
                }

                return "";
            } else {
                System.out.println("Error in connection. Status Code: " + statusCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String sendQuery(String input) {
        return null;
    }
}
