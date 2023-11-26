package com.example.englishlearningappv1.API;

import java.io.IOException;
import java.sql.SQLException;

public class APIController {
    static ChatBot chatBot;
    static SaA saA = new SaA();

    static {
        try {
            chatBot = new ChatBot();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    GoogleTranslate googleTranslate = new GoogleTranslate();
    static TTS tts;

    static {
        try {
            tts = new TTS();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public APIController() throws SQLException {
    }

    public static String chatBotSendQuery(String input) {
        return chatBot.sendQuery(input);
    }

    public static void speak(String input) {
        tts.speak(input);
    }

    public static String translate(String input, String langFrom, String langTo) {
        return GoogleTranslate.translate(input, langFrom, langTo);
    }

    public static String sendRequestToSaA(String input) throws SQLException, IOException {
        return saA.sendQuery(input);
    }
}
