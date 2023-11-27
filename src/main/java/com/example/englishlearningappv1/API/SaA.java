package com.example.englishlearningappv1.API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;

public class SaA implements APInterface {
    final static String endpoint = "http://thesaurus.altervista.org/thesaurus/v1";
    final static String api_key;// replace "test_only" with your own key (http://thesaurus.altervista.org/mykey)

    static {
        try {
            api_key = API_KEY.getAPIKey("SaA-api-key").getKey();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void SendRequest(String word, String language, String key) {
        try {
            URL serverAddress = new URL(endpoint + "?word=" + URLEncoder.encode(word, "UTF-8") + "&language=" + language + "&key=" + key + "&output=json");
            HttpURLConnection connection = (HttpURLConnection) serverAddress.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            System.out.println(root.path("fact").asText());
            // Use Gson to parse the JSON string
            JsonArray jsonArray = JsonParser.parseString(root.toString()).getAsJsonObject().getAsJsonArray("response");

            System.out.println(root.toString());
            // Iterate through the array and print the values
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                JsonObject listObject = jsonObject.getAsJsonObject("list");
                String category = listObject.get("category").getAsString();
                String synonyms = listObject.get("synonyms").getAsString();
//                String acronyms = listObject.get("acronyms").getAsString();


                System.out.println("Category: " + category);
                System.out.println("Synonyms: " + synonyms);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendQuery(String input) throws IOException, SQLException {
        URL url = new URL("https://api.api-ninjas.com/v1/thesaurus?word=" + input);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");

        connection.setRequestProperty("X-Api-Key", API_KEY.getAPIKey("SaA-api-key").getKey());
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        // Parse the JSON data
        JSONObject jsonObject = new JSONObject(root.toString());

        // Accessing individual elements
        String word = jsonObject.getString("word");
        JSONArray synonyms = jsonObject.getJSONArray("synonyms");
        JSONArray antonyms = jsonObject.getJSONArray("antonyms");
        // Print the results
        System.out.println("Word: " + word);
        System.out.println("Synonyms: ");
        String syn = "\n";
        for (Object jsonObject1 : synonyms) {
            syn += "- " + jsonObject1.toString() + " \n";
        }

        if (syn.equals("\n")) {
            syn = "There are no synonyms";
        }
        String an = "\n";
        System.out.println();
        System.out.println("Antonyms: ");
        for (Object jsonObject1 : antonyms) {
            an += "- " + jsonObject1.toString() + " \n";
        }
        if (an.equals("\n")) {
            an = "There are no antonyms";
        }

        return word + "\n Synonyms: " + syn + "\n Antonyms: " + an;
    }

}
