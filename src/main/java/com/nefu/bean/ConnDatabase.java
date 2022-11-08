package com.nefu.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDatabase {
    public static final String URL = "jdbc:mysql://192.168.1.150:3306/bookshop?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "1111";
    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
}
