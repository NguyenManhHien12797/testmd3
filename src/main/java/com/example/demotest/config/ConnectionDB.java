package com.example.demotest.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection connect;

    public static final String URL = "jdbc:mysql://localhost:3306/testmd3";
    public static final String USER = "root";
    public static final String PASSWORD = "200525081997";

    public ConnectionDB() {
    }


    public static Connection getConnect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(URL,USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;

    }
}
