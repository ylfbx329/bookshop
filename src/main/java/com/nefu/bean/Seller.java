package com.nefu.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Seller {
    private String seller_id;
    private String phone;
    private String password;
    private String shop_name;

    public Seller() {
    }

    public int addSeller(Seller sellers) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "insert into seller (seller_id,phone, password, shop_name) values (?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, sellers.getSeller_id());
        preparedStatement.setString(2, sellers.getPhone());
        preparedStatement.setString(3, sellers.getPassword());
        preparedStatement.setString(4, sellers.getShop_name());
        int n = preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
        return n;
    }

    public boolean querySeller(Seller seller) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "select * from seller where phone = ? and password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, seller.getPhone());
        preparedStatement.setString(2, seller.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean flag = resultSet.next();
        if (flag) {
            seller.setSeller_id(resultSet.getString("seller_id"));
            seller.setShop_name(resultSet.getString("shop_name"));
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
        return flag;
    }

    public boolean login() {
        try {
            if (querySeller(this)) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean register() {
        try {
            if (addSeller(this) > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String getSeller_id() {
        if (seller_id == null)
            seller_id = UUID.randomUUID().toString().replace("-", "");
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
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

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
}
