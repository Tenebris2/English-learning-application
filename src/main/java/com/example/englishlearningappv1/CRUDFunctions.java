package com.example.englishlearningappv1;

import java.sql.*;

public class CRUDFunctions{
    static final String dbURL = "jdbc:mysql://localhost:3306/dictionary";
    static final String username = "root";
    static final String password = "";
    private static final String SPLITTING_CHAR = "<html>";

    static Connection connection;
    static void connectDB()
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
    }
    public static void CRUDsearchWord(String searchedWord) throws SQLException {

        String tmp ='"' + searchedWord + "%"  +'"';
        String sql = "SELECT * FROM wordlist WHERE english LIKE " + tmp + "";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String word = result.getString("english");
            String definition = result.getString("definition");
            int id = result.getInt("id");

            System.out.println(word + "\t"  + definition + "\t" + id);
        }
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
    }
    public static void CRUDdeleteWord(String word) throws SQLException {
        String sql = "DELETE FROM wordlist WHERE english=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, word);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A word was deleted successfully!");
        }
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
    }

}
