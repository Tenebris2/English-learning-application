package com.example.englishlearningappv1.Functions;

import com.example.englishlearningappv1.Root;
import com.example.englishlearningappv1.Speaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SpellingFunction implements Function {
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
