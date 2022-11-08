package com.nefu.servlet;

import com.alibaba.fastjson2.JSON;
import com.nefu.bean.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BuyBookServlet", value = "/BuyBookServlet")
public class BuyBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user_id = request.getParameter("user_id");
        String str_order = request.getParameter("order");
//        System.out.println("buy user_id -> " + user_id);
//        System.out.println("buy str_order -> " + str_order);
        List<Order> orders = JSON.parseArray(str_order, Order.class);
//        System.out.println("buy orders -> " + orders);
        for (Order order : orders) {
            order.setUser_id(user_id);
            System.out.println(order.getUser_id());
            System.out.println(order.getBook_id());
            System.out.println(order.getOrder_mount());
        }
        try {
            if (Order.creatOrder(orders)) {
                response.setHeader("REDIRECT", "REDIRECT");
                response.setHeader("CONTENTPATH", "shopping_cart.jsp");
//                response.sendRedirect("shopping_cart.jsp");
            } else {
                out.print("出错了，3秒后跳转至商品页面，请重新购买");
                response.setHeader("refresh", "3;URL=user_index.jsp");
                out.print("若没有跳转请点击<a href=user_index.jsp>这里</a>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
