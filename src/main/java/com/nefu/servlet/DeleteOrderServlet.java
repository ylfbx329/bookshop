package com.nefu.servlet;

import com.nefu.bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteOrderServlet", value = "/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order_id = request.getParameter("order_id");
        try {
            int n = Order.deleteOrder(order_id);
            // 返回不走过滤器，需要单独设置类型和编码
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(String.valueOf(n));
            System.out.println("DeleteOrderServlet deleteOrder -> " + n);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
