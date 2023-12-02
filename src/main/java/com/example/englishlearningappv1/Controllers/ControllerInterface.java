package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Utils.FunctionEffects;

public interface ControllerInterface {
    int width = 33;
    int height = 33;
    String SCANNER_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/ScannerPage.fxml";
    String DICTIONARY_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/JavaFx.fxml";
    String HOME_PAGE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/HomePage.fxml";
    String QUIZ_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/home.fxml";
    String GOOGLE_TRANSLATE_WINDOW_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/GoogleTranslate.fxml";
    String GAME_HUB_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/gameHub.fxml";
    String HELLO_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/Hello.fxml";
    String FAVORITES_PAGE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/FavoritesPage.fxml";
    String RESULT_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/result.fxml";
    String IMAGE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/imageFXML.fxml";
    FunctionEffects functionEffects = new FunctionEffects();
}
