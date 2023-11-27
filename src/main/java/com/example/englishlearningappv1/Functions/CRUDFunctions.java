package com.example.englishlearningappv1.Functions;

import com.example.englishlearningappv1.Database.DBConnection;
import com.example.englishlearningappv1.Database.WordServiceImpl;
import com.example.englishlearningappv1.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDFunctions implements Function {

    public static Connection connection;
    public static void connectDB()
    {
        connection = DBConnection.getInstance().getConnection();
    }
    public static void CRUDaddWord(String word,String definition) throws SQLException {
        WordServiceImpl.getInstance().addWord(word, definition);
    }
    public static String CRUDsearchWord(String searchedWord) throws SQLException {
        return WordServiceImpl.getInstance().searchWord(searchedWord);
    }
    public static void CRUDdeleteWord(String word) throws SQLException {
        WordServiceImpl.getInstance().deleteWord(word);
    }
    public static void CRUDupdateWord(String word,String definition) throws SQLException {
        WordServiceImpl.getInstance().updateWord(word, definition);
    }

    public static void addFavoriteWord(String word) throws SQLException {
        WordServiceImpl.getInstance().addFavoriteWord(word);
    }

    public static void deleteFavoriteWord(String word) throws SQLException {
        WordServiceImpl.getInstance().deleteFavoriteWord(word);
    }


    public static List<String> createFavoriteWordList() throws SQLException {
        return WordServiceImpl.getInstance().createFavoriteWordList();
    }


    public static void main(String[] args) throws SQLException {
        connectDB();
        CRUDFunctions.CRUDsearchWord("hello");
    }
}
