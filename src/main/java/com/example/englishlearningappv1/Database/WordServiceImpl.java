package com.example.englishlearningappv1.Database;

import com.example.englishlearningappv1.Word;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class WordServiceImpl implements WordService {
    private final WordDao wordDao;
    private static final WordServiceImpl wordService = new WordServiceImpl(new WordDaoImpl());
    private WordServiceImpl(WordDao wordDao) {
        this.wordDao = wordDao;
    }
    public static WordServiceImpl getInstance() {
        return wordService;
    }
    @Override
    public String searchWord(String word) throws SQLException {
        return wordDao.search(word);
    }

    @Override
    public void addWord(String word, String def) throws SQLException {
        wordDao.add(word, def);
    }

    @Override
    public void updateWord(String word, String def) throws SQLException {
        wordDao.update(word, def);
    }

    @Override
    public void deleteWord(String word) throws SQLException {
        wordDao.delete(word);
    }

    @Override
    public Map<String, Word> createWordList() throws SQLException {
        return wordDao.createWordList();
    }

    @Override
    public void addFavoriteWord(String word) throws SQLException {
        wordDao.addFavoriteWord(word);
    }

    @Override
    public void deleteFavoriteWord(String word) throws SQLException {
        wordDao.deleteFavoriteWord(word);
    }

    @Override
    public List<String> createFavoriteWordList() throws SQLException {
        return wordDao.createFavoriteWordList();
    }

}
