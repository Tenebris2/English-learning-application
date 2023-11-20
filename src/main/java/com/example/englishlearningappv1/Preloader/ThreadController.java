package com.example.englishlearningappv1.Preloader;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.security.Key;

import static com.example.englishlearningappv1.GoogleTranslate.translate;

public class ThreadController {
    public static void simulateDelay() {
        Thread delayThread = new Thread(() -> {
            try {
                // Introduce a delay (e.g., 3 seconds)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // Handle the InterruptedException if needed
                e.printStackTrace();
            }

        });

        delayThread.start();
    }
}
