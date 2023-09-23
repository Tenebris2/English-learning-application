module com.example.englishlearningappv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.englishlearningappv1 to javafx.fxml;
    exports com.example.englishlearningappv1;

    requires javafx.web;
    requires freetts;
    requires java.sql;
}