package com.nefu.bean;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {
    private String book_id;
    private String seller_id;
    private String isbn;
    private String book_name;
    private String author;
    private String press;
    private Date pub_date;
    private BigDecimal price;
    private int store_mount;
    private int deal_mount;
    private byte[] img;
    private String shop_name;

    public Book() {
    }

    public Book(String book_name, String author, String press, BigDecimal price, byte[] img, String shop_name) {
        this.book_name = book_name;
        this.author = author;
        this.press = press;
        this.price = price;
        this.img = img;
        this.shop_name = shop_name;
    }

    public static int addBook(Book book) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "insert into book (book_id,seller_id, isbn, book_name, author, press, pub_date, price, store_mount, deal_mount, img) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, book.getBook_id());
        preparedStatement.setString(2, book.getSeller_id());
        preparedStatement.setString(3, book.getIsbn());
        preparedStatement.setString(4, book.getBook_name());
        preparedStatement.setString(5, book.getAuthor());
        preparedStatement.setString(6, book.getPress());
        preparedStatement.setDate(7, book.getPub_date());
        preparedStatement.setBigDecimal(8, book.getPrice());
        preparedStatement.setInt(9, book.getStore_mount());
        preparedStatement.setInt(10, book.getDeal_mount());
        preparedStatement.setBytes(11, book.getImg());
        int n = preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
        return n;
    }

    public static int updateBook(Book book) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        String sql = "update book set isbn = ?, book_name = ?,author = ?, press = ?,pub_date = ?,price = ?,store_mount = ?,img = ? where book_id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, book.getIsbn());
        pst.setString(2, book.getBook_name());
        pst.setString(3, book.getAuthor());
        pst.setString(4, book.getPress());
        pst.setDate(5, book.getPub_date());
        pst.setBigDecimal(6, book.getPrice());
        pst.setInt(7, book.getStore_mount());
        pst.setBytes(8, book.getImg());
        pst.setString(9, book.getBook_id());
        int n = pst.executeUpdate();
        pst.close();
        conn.close();
        return n;
    }

    public static List<Book> queryBook(String seller_id) throws SQLException {
        Connection conn = ConnDatabase.getConnection();
        Statement statement = conn.createStatement();
        String sql = "select * from book ";
        if (seller_id != null) {
            sql += "where seller_id = '" + seller_id + "'";
            System.out.println("queryBook sql -> " + sql);
        } else {
            sql += "inner join seller on seller.seller_id = book.seller_id";
        }
        System.out.println("queryBook sql -> " + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        List<Book> books = new ArrayList<>();
        Book book;
        while (resultSet.next()) {
            book = new Book();
            book.setBook_id(resultSet.getString("book_id"));
            book.setSeller_id(resultSet.getString("seller_id"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setBook_name(resultSet.getString("book_name"));
            book.setAuthor(resultSet.getString("author"));
            book.setPress(resultSet.getString("press"));
            book.setPub_date(resultSet.getDate("pub_date"));
            book.setPrice(resultSet.getBigDecimal("price"));
            book.setStore_mount(resultSet.getInt("store_mount"));
            book.setDeal_mount(resultSet.getInt("deal_mount"));
            book.setImg(resultSet.getBytes("img"));
            if (seller_id == null)
                book.setShop_name(resultSet.getString("shop_name"));
            books.add(book);
        }
        resultSet.close();
        statement.close();
        conn.close();
        return books;
    }

    public String getBook_id() {
        if (book_id == null)
            book_id = UUID.randomUUID().toString().replace("-", "");
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStore_mount() {
        return store_mount;
    }

    public void setStore_mount(int store_mount) {
        this.store_mount = store_mount;
    }

    public int getDeal_mount() {
        return deal_mount;
    }

    public void setDeal_mount(int deal_mount) {
        this.deal_mount = deal_mount;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
}
