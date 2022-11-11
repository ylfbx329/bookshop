package com.nefu.servlet;

import com.nefu.bean.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "PayOrderServlet", value = "/PayOrderServlet")
public class PayOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        try {
            BigDecimal price = Order.payOrder(user_id);
            if (price != null) {
                response.setHeader("REDIRECT", "REDIRECT");
                response.setHeader("CONTENTPATH", "pay.jsp?price=" + price);
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
