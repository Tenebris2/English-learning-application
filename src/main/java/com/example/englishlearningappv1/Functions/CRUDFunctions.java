package com.example.englishlearningappv1.Functions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDFunctions{
    static final String dbURL = "jdbc:mysql://localhost:3306/dictionary";
    static final String username = "root";
    static final String password = "";
    private static final String SPLITTING_CHAR = "<html>";

    public static Connection connection;
    public static void connectDB()
    {
        try {
            connection = DriverManager.getConnection(dbURL,username,password);
            System.out.println("Database connection sucessful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void CRUDaddWord(String word,String definition) throws SQLException {
        String sql = "INSERT INTO wordlist (english,definition) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);
        statement.setString(2, definition);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }

        statement.close();
    }
    public static String CRUDsearchWord(String searchedWord) throws SQLException {

        String tmp ='"' + searchedWord + "%"  +'"';
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
    public static void CRUDshowAllWords() throws SQLException {
        String sql = "SELECT * FROM wordlist";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            String word = result.getString("english");
            String definition = result.getString("definition");
            int id = result.getInt("id");

            System.out.println(word + "\t"  + definition + "\t" + id);
        }

        statement.close();
        result.close();
    }
    public static void CRUDdeleteWord(String word) throws SQLException {
        String sql = "DELETE FROM wordlist WHERE english=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A word was deleted successfully!");
        }

        statement.close();
    }
    public static void CRUDupdateWord(String word,String definition) throws SQLException {
        String sql = "UPDATE wordlist SET english=?,definition=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);
        statement.setString(2, definition);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing word was updated successfully!");
        }

        statement.close();

    }
    public static void CRUDAddQuestion(String Question,String answerList,String correctAnswer) throws SQLException {
        String sql = "INSERT INTO question_list (question,answer_list,correct_answer) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, Question);
        statement.setString(2, answerList);
        statement.setString(3, correctAnswer);
        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("A new question was inserted successfully!");
        }

        statement.close();
    }
    public static void CRUDShowAllQuestions() throws SQLException {
        String sql = "SELECT * FROM question_list";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            String question = result.getString("question");
            String answerList = result.getString("answer_list");
            String correct_answer = result.getString("correct_answer");

            System.out.println(question + "\n"  + answerList + "\n" + correct_answer);
        }
        statement.close();
        result.close();
    }

    public static void addFavoriteWord(String word) throws SQLException {
        String sql = "INSERT INTO favorite_word (favorite_word) VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);

        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("A new favorite word has been inserted successfully!");
        }

        statement.close();
    }

    public static void deleteFavoriteWord(String word) throws SQLException {
        String sql = "DELETE FROM favorite_word WHERE favorite_word=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A word was deleted successfully!");
        }

        statement.close();
    }


    public static List<String> createFavoriteWordList() throws SQLException {
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

    public static String searchForSpecificWord(String searchedWord) throws SQLException {

        String tmp ='"' + searchedWord + "%"  +'"';
        String sql = "SELECT * FROM wordlist WHERE english = " + tmp + "";

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

    public static void main(String[] args) throws SQLException {
        connectDB();
        CRUDFunctions.CRUDsearchWord("hello");
    }
}
