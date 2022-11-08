package com.nefu.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class User {
    private String user_id;
    private String phone;
    private String password;
    private String nick_name;

    public User() {
    }

    public int addUser(User user) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "insert into user (user_id,phone, password, nick_name) values (?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, user.getUser_id());
        preparedStatement.setString(2, user.getPhone());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getNick_name());
        int n = preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
        return n;
    }

    public boolean queryUser(User user) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "select * from user where phone = ? and password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, user.getPhone());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean flag = resultSet.next();
        if (flag) {
            user.setUser_id(resultSet.getString("user_id"));
            user.setNick_name(resultSet.getString("nick_name"));
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
        return flag;
    }

    public boolean login() {
        try {
            if (queryUser(this)) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public boolean register() {
        try {
            if (addUser(this) > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public String getUser_id() {
        if (user_id == null)
            user_id = UUID.randomUUID().toString().replace("-", "");
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
