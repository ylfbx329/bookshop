package com.nefu.servlet;

import com.alibaba.fastjson2.JSON;
import com.nefu.bean.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddOrderServlet", value = "/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        String str_order = request.getParameter("order");
        List<Order> orders = JSON.parseArray(str_order, Order.class);
        for (Order order : orders) {
            order.setUser_id(user_id);
        }
        try {
            if (Order.addOrder(orders) > 0) {
                response.setHeader("REDIRECT", "REDIRECT");
                response.setHeader("CONTENTPATH", "shopping_cart.jsp");
            } else {
                response.setHeader("ERROR", "ERROR");
                response.setHeader("ERRORMESSAGE", "服务器错误");
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
