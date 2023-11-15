package com.example.englishlearningappv1;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.englishlearningappv1.Functions.CRUDFunctions.connectDB;

public class Start {
    public static void main(String[] args) throws SQLException, IOException {
        connectDB();
        HomePage.main(args);


    }
}

