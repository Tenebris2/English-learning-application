package com.example.englishlearningappv1;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SpellingFunction {
    private Button spellingBtn;
    private final String tag ="#speak";

    public SpellingFunction(Scene scene){
        spellingBtn = (Button) scene.lookup(tag);
    }

    public void spellCurrentWord(Root root) {
        spellingBtn.setOnMouseClicked(e -> {
            String word = root.getCurrent();
            Speaker.speak(word);
        });
    }

}
