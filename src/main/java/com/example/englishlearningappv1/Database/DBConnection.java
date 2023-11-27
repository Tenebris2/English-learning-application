package com.example.englishlearningappv1.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;
    static final String dbURL = "jdbc:mysql://localhost:3306/dictionary";
    static final String username = "root";
    static final String password = "";
    private DBConnection() {
        try {
            connection = DriverManager.getConnection(dbURL,username,password);
            System.out.println("Database connection sucessful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
