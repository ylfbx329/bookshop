package com.nefu.servlet;

import com.nefu.bean.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "DeleteOrderServlet", value = "/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("DeleteOrderServlet id -> " + id);
        boolean pay_flag = request.getParameter("pay_flag").equals("1");
        System.out.println("DeleteOrderServlet pay_flag -> " + request.getParameter("pay_flag"));
        try {
            BigDecimal price = null;
            if (pay_flag) {
                price = Order.getSumPrice(id);
            }
            int n = Order.deleteOrder(id, pay_flag);
            // 返回不走过滤器，需要单独设置类型和编码
//            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(String.valueOf(n));
            System.out.println("DeleteOrderServlet n -> " + String.valueOf(n));
            if (pay_flag) {
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
