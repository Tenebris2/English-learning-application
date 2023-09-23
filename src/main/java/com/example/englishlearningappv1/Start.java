package com.example.englishlearningappv1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.englishlearningappv1.CRUDFunctions.connectDB;

import static com.example.englishlearningappv1.MultipleChoice.makeQuestion;

public class Start {
    public static void main(String[] args) throws SQLException {
        connectDB();
        DictionaryApp.main(args);
    }
}

