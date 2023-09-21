import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslate {
    private static final String ENG_LANG = "en";
    private static final String VIE_LANG = "vi";

    //API translate
    public static String translate(String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbxueKM8M8EMiiQYoYEmTZZR3L-TQ61S65DVeNvUo5DaxWbFUgw5/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + VIE_LANG +
                "&source=" + ENG_LANG;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
        conection.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
