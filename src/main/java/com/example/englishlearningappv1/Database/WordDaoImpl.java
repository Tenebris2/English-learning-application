package com.example.englishlearningappv1.Database;

import com.example.englishlearningappv1.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordDaoImpl implements WordDao {
    private final String SPLITTING_CHAR = "<html>";
    private static Connection connection = null;
    public WordDaoImpl() {
        connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public String search(String input) throws SQLException {
        String tmp ='"' + input + "%"  +'"';
        String sql = "SELECT * FROM wordlist WHERE english LIKE " + tmp + "";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        String word = null;
        String def = null;
        while (result.next()){
            word = result.getString("english");
            def = result.getString("definition");
            int id = result.getInt("id");

            System.out.println(word + "\t"  + def + "\t" + id);
        }

        statement.close();
        result.close();

        return def;
    }

    @Override
    public void add(String input, String def) throws SQLException {
        String sql = "INSERT INTO wordlist (english,definition) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, input);
        statement.setString(2, def);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new word was inserted successfully!");
        }

        statement.close();
    }

    @Override
    public void update(String input, String second_input) throws SQLException {
        String sql = "UPDATE wordlist SET english=?,definition=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, input);
        statement.setString(2, second_input);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing word was updated successfully!");
        }

        statement.close();
    }

    @Override
    public void delete(String input) throws SQLException {
        String sql = "DELETE FROM wordlist WHERE english=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, input);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A word was deleted successfully!");
        }

        statement.close();
    }

    public Map<String, Word> createWordList() throws SQLException {
        Map<String, Word> wordList = new TreeMap<>();
        String sql = "SELECT * FROM wordlist";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        String line;

        while (result.next()){
            String word = result.getString("english");
            String definition = result.getString("definition");
            line = word + definition;

            String[] parts = line.split(SPLITTING_CHAR);
            parts[1] = "<html>" + parts[1];
            wordList.put(parts[0], new Word(parts[0], parts[1]));
        }

        statement.close();
        result.close();

        return wordList;
    }

    @Override
    public void addFavoriteWord(String word) throws SQLException {
        String sql = "INSERT INTO favorite_word (favorite_word) VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);

        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("A new favorite word has been inserted successfully!");
        }

        statement.close();
    }

    @Override
    public void deleteFavoriteWord(String word) throws SQLException {
        String sql = "DELETE FROM favorite_word WHERE favorite_word=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A word was deleted successfully!");
        }

        statement.close();
    }

    @Override
    public List<String> createFavoriteWordList() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM favorite_word";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            String word = result.getString("favorite_word");
            list.add(word);
        }

        statement.close();
        result.close();

        return list;
    }

}
