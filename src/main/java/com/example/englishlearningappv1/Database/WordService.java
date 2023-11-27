package com.example.englishlearningappv1.Database;

import com.example.englishlearningappv1.Word;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface WordService {
    String searchWord(String word) throws SQLException;
    void addWord(String word, String def) throws SQLException;
    void updateWord(String word, String def) throws SQLException;
    void deleteWord(String word) throws SQLException;
    Map<String, Word> createWordList() throws SQLException;
    void addFavoriteWord(String word) throws SQLException;
    void deleteFavoriteWord(String word) throws SQLException;
    List<String> createFavoriteWordList() throws SQLException;
}
