package com.nefu.servlet;

import com.nefu.bean.Book;
import com.nefu.bean.Seller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "AddBookServlet", value = "/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = new Book();
        Seller seller = (Seller) request.getSession().getAttribute("seller");
        book.setSeller_id(seller.getSeller_id());
        book.setIsbn(request.getParameter("isbn"));
        book.setBook_name(request.getParameter("book_name"));
        book.setAuthor(request.getParameter("author"));
        book.setPress(request.getParameter("press"));
        book.setPub_date(Date.valueOf(request.getParameter("pub_date")));
        book.setShop_name(seller.getShop_name());
        book.setPrice(new BigDecimal(request.getParameter("price")));
        book.setStore_mount(Integer.parseInt(request.getParameter("store_mount")));
        book.setImg(request.getParameter("img").getBytes());
        try {
            int n = Book.addBook(book);
            if (n > 0) {
                response.sendRedirect("seller_index.jsp");
            } else {
                PrintWriter out = response.getWriter();
                out.print("添加失败，请检查图书信息");
                response.setHeader("refresh", "3;URL=add_book.jsp");
                out.print("若没有跳转请点击<a href=add_book.jsp>这里</a>");
                out.flush();
                out.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
