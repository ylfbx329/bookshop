package com.nefu.bean;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Order {
    private String order_id;
    private String user_id;
    private String book_id;
    private int order_mount;
    private BigDecimal price;

    public Order() {
    }

    public Order(String order_id, int order_mount, BigDecimal price) {
        this.order_id = order_id;
        this.order_mount = order_mount;
        this.price = price;
    }

    public static int addOrder(List<Order> orders) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "insert into `order` (order_id, user_id, book_id, order_mount, price) values (?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        int n = 0;
        for (Order order : orders) {
            pst.setString(1, order.getOrder_id());
            pst.setString(2, order.getUser_id());
            pst.setString(3, order.getBook_id());
            pst.setInt(4, order.getOrder_mount());
            pst.setBigDecimal(5, order.getPrice());
            n = pst.executeUpdate();
        }
        pst.close();
        conn.close();
        return n;
    }

    public static int deleteOrder(String order_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "delete from `order` where order_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, order_id);
        int n = pst.executeUpdate();
        pst.close();
        conn.close();
        return n;
    }

    public static List<Pair<Order, Book>> queryOrder(String user_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "select * from `order` where user_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user_id);
        ResultSet resultSet = pst.executeQuery();
        List<Pair<Order, Book>> orders = new ArrayList<>();
        Pair<Order, Book> orderBookMap;
        Order order;
        Book book;
        while (resultSet.next()) {
            order = new Order(resultSet.getString("order_id"), resultSet.getInt("order_mount"), resultSet.getBigDecimal("price"));
            book = Book.getBook(resultSet.getString("book_id"));
            orderBookMap = new Pair<>(order, book);
            orders.add(orderBookMap);
        }
        pst.close();
        resultSet.close();
        conn.close();
        return orders;
    }

    public static BigDecimal payOrder(String user_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "delete from `order` where user_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user_id);
        BigDecimal price = getSumPrice(user_id);
        int n = pst.executeUpdate();
        pst.close();
        conn.close();
        return n > 0 ? price : null;
    }

    public static BigDecimal getSumPrice(String user_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "select book_id, price, order_mount from `order` where user_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user_id);
        ResultSet resultSet = pst.executeQuery();
        BigDecimal price = new BigDecimal(0);
        while (resultSet.next()) {
            price = price.add(resultSet.getBigDecimal("price"));
            Book.updateBookNum(resultSet.getString("book_id"), resultSet.getInt("order_mount"));
        }
        resultSet.close();
        pst.close();
        conn.close();
        return price;
    }

    public String getOrder_id() {
        if (order_id == null)
            order_id = UUID.randomUUID().toString().replace("-", "");
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public int getOrder_mount() {
        return order_mount;
    }

    public void setOrder_mount(int order_mount) {
        this.order_mount = order_mount;
    }

    public BigDecimal getPrice() throws SQLException {
        if (price == null) {
            price = Book.getBook(book_id).getPrice().multiply(BigDecimal.valueOf(order_mount));
        }
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
