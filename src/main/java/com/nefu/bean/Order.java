package com.nefu.bean;

import javafx.util.Pair;

import javax.servlet.annotation.WebServlet;
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

    public Order(String book_id, int order_mount) {
        this.book_id = book_id;
        this.order_mount = order_mount;
    }

    public Order(String order_id, int order_mount, BigDecimal price) {
        this.order_id = order_id;
        this.order_mount = order_mount;
        this.price = price;
    }

    public Order(String user_id, String book_id, int order_mount) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.order_mount = order_mount;
    }

    public Order(String order_id, String user_id, String book_id, int order_mount, BigDecimal price) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.order_mount = order_mount;
        this.price = price;
    }

    public static int addOrder(List<Order> orders) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "insert into `order` (order_id, user_id, book_id, order_mount, price) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        int n = 0;
        for (Order order : orders) {
            preparedStatement.setString(1, order.getOrder_id());
            preparedStatement.setString(2, order.getUser_id());
            preparedStatement.setString(3, order.getBook_id());
            preparedStatement.setInt(4, order.getOrder_mount());
            preparedStatement.setBigDecimal(5, order.getPrice());
            n = preparedStatement.executeUpdate();
            System.out.println(order.getUser_id());
            System.out.println(order.getBook_id());
            System.out.println(order.getOrder_mount());
            System.out.println(order.getPrice());
        }
        System.out.println("addOrder -> " + sql);
        preparedStatement.close();
        conn.close();
        return n;
    }

    public static int deleteOrder(String order_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "delete from `order` where order_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, order_id);
        System.out.println("deleteOrder sql -> " + sql + order_id);
        int n = pst.executeUpdate();
        pst.close();
        conn.close();
        return n;
    }

    public static List<Pair<Order, Book>> queryOrder(String user_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "select * from `order` where user_id = ?";
        PreparedStatement orderPst = conn.prepareStatement(sql);
        sql = "select * from book inner join seller on book.seller_id = seller.seller_id where book_id = ?";
        PreparedStatement bookPst = conn.prepareStatement(sql);
        orderPst.setString(1, user_id);
        ResultSet orderSet = orderPst.executeQuery();
        ResultSet bookSet = null;
        List<Pair<Order, Book>> orders = new ArrayList<>();
        Pair<Order, Book> orderBookMap;
        Order order = null;
        Book book = null;
        while (orderSet.next()) {
            bookPst.setString(1, orderSet.getString("book_id"));
            bookSet = bookPst.executeQuery();
            order = new Order(orderSet.getString("order_id"), orderSet.getInt("order_mount"), orderSet.getBigDecimal("price"));
            if (bookSet.next())
                book = new Book(bookSet.getString("book_name"), bookSet.getString("author"), bookSet.getString("press"), bookSet.getBigDecimal("price"), bookSet.getBytes("img"), bookSet.getString("shop_name"));
            orderBookMap = new Pair<>(order, book);
            orders.add(orderBookMap);
        }
        orderPst.close();
        bookPst.close();
        orderSet.close();
        if (bookSet != null) {
            bookSet.close();
        }
        conn.close();
        return orders;
    }

    public static boolean creatOrder(List<Order> orders) throws SQLException {
        System.out.println("creatOrder -> start");
        if (addOrder(orders) > 0) {
            return true;
        }
        System.out.println("creatOrder -> end");
        return false;
    }

    public BigDecimal queryPrice(String book_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "select price from book where book_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, book_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        BigDecimal price = null;
        if (resultSet.next()) {
            price = new BigDecimal(String.valueOf(resultSet.getBigDecimal("price")));
        }
        resultSet.close();
        preparedStatement.close();
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
        if (this.price != null) {
            System.out.println("Order this.price -> " + price);
            return this.price;
        }
        BigDecimal price = queryPrice(this.book_id);
        System.out.println("Order price -> " + price);
        if (price != null) {
            this.price = price.multiply(BigDecimal.valueOf(this.order_mount));
        }
        System.out.println("Order price return -> " + this.price);
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
