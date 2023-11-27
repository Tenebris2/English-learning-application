package com.example.englishlearningappv1.API;

import com.example.englishlearningappv1.Database.DBConnection;
import com.example.englishlearningappv1.Functions.CRUDFunctions;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;

import static com.example.englishlearningappv1.Functions.CRUDFunctions.connection;

public class API_KEY {
    private String key;
    private String name;

    static final String dbURL = "jdbc:mysql://localhost:3306/dictionary";
    static final String username = "root";
    static final String password = "";

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public static void connectDB()
    {
        connection = DBConnection.getInstance().getConnection();
    }
    public API_KEY(String key, String name) throws SQLException {
        this.key = key;
        this.name = name;
    }

    public static void add(API_KEY key) throws SQLException {
        String sql = "INSERT INTO api_keys (api_key,api_name) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, key.key);
        statement.setString(2, key.name);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }

        statement.close();
    }

    public static API_KEY getAPIKey(String name) throws SQLException {
        connectDB();

        String tmp ='"' + name  +'"';
        String sql = "SELECT * FROM api_keys WHERE api_name = " + tmp + "";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        String key = null;
        String name_of_api = null;

        while (result.next()){
            key = result.getString("api_key");
            name_of_api = result.getString("api_name");
        }

        statement.close();
        result.close();

        return new API_KEY(key, name_of_api);
    }

    public static void main(String[] args) throws SQLException {
    }
}
