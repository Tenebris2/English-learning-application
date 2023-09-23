package com.example.englishlearningappv1;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.englishlearningappv1.CRUDFunctions.connectDB;

public class Controller {
    private Root root;
    private WordViewList wordViewList;
    private DefView defView;
    private FindField findField;
    private SpellingFunction spellingFunction;
    private DeletingFunction deletingFunction;
    private AddingFunction addingFunction;
    private ChangingFunction changingFunction;
    private GoogleSearchingFunction googleSearchingFunction;
    private User user;
    private VipFunction vipFunction;

    public void init(Scene scene) {
        root = new Root();
        wordViewList = new WordViewList(scene);
        defView = new DefView(scene);
        findField = new FindField(scene);
        spellingFunction = new SpellingFunction(scene);
        deletingFunction = new DeletingFunction(scene);
        addingFunction = new AddingFunction(scene);
        changingFunction = new ChangingFunction(scene);
        googleSearchingFunction = new GoogleSearchingFunction(scene);
        vipFunction = new VipFunction(scene);
        user = new User(scene);
    }

    public void initData() throws IOException, SQLException {
        root.createWordList();
        root.loadHistorySearch();
        wordViewList.loadWords();
        wordViewList.loadDef(defView, findField);
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    }

    public void addFunction() {
        findField.clickToFindDef(defView, wordViewList);
        findField.addListenerToTextField(defView, wordViewList);
        findField.setMouseEventToSearchView();
        findField.loadSearchViewList(defView, wordViewList);
        findField.enterToFindDef(defView, wordViewList);
        spellingFunction.spellCurrentWord(root);
        deletingFunction.setDeletingFunction(wordViewList, defView);
        addingFunction.setAddingBtn(wordViewList);
        changingFunction.setChangingBtn(wordViewList, defView);
        googleSearchingFunction.setGoogleSearchBtn(defView, findField);
        vipFunction.setVipBtn();
        user.setUserBtn();
    }

    public void hideSearchView(Parent root) {
        root.setOnMouseClicked(e -> {
            findField.hideSearchView();
        });
    }

    public boolean getChanged(){
        return root.getChanged();
    }
}
