package com.example.englishlearningappv1.Preloader;

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
