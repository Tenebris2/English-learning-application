module com.example.englishlearningappv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.englishlearningappv1 to javafx.fxml;
    exports com.example.englishlearningappv1;
    exports com.example.englishlearningappv1.Functions;
    opens com.example.englishlearningappv1.Functions to javafx.fxml;
    exports com.example.englishlearningappv1.Controllers;
    opens com.example.englishlearningappv1.Controllers to javafx    .fxml;

    requires javafx.web;
    requires freetts;
    requires java.sql;
}