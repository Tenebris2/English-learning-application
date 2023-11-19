package com.example.englishlearningappv1;

import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;

public class DefView extends Root{
    private WebView defView;
    private String tag = "#defView";

    //Khởi tạo WebView xem nghĩa
    public DefView(Scene scene){
        defView = (WebView) scene.lookup(tag);
        defView.setStyle("-fx-opacity: 0.5;");
}


    //Hiển thị nghĩa
    public void representDef(String def){
        if(def != null){
            defView.getEngine().loadContent(def, "text/html");
            defView.setStyle("-fx-opacity: 0.5;");
        }else {
            clearDefView();
        }
    }

    //xóa hết nd trên webview
    public void clearDefView(){
        defView.getEngine().loadContent("", "text/html");
        defView.setStyle("-fx-opacity: 0.5;");
    }

    //Hiển thị nghĩa mới lên
    public void loadNewDef(String word){
        defView.getEngine().loadContent(word, "text/html");
        defView.setStyle("-fx-opacity: 0.5;");
    }
}
