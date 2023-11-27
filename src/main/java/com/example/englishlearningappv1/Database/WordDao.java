package com.example.englishlearningappv1.Database;

import com.example.englishlearningappv1.Word;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface WordDao extends DAO {
    public Map<String, Word> createWordList() throws SQLException;
    public void addFavoriteWord(String word) throws SQLException;
    void deleteFavoriteWord(String word) throws SQLException;
    List<String> createFavoriteWordList() throws SQLException;
}
