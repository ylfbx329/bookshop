package com.nefu.servlet;

import com.nefu.bean.Seller;
import com.nefu.bean.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        boolean isuser = request.getParameter("isuser").equals("1");
        request.getSession().setAttribute("isuser", isuser);
        boolean flag;
        String target = "html/welcome.html";
        if (isuser) {
            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            flag = user.login();
            if (flag) {
                request.getSession().setAttribute("user", user);
                target = "user_index.jsp";
            }
        } else {
            Seller seller = new Seller();
            seller.setPhone(phone);
            seller.setPassword(password);
            flag = seller.login();
            if (flag) {
                request.getSession().setAttribute("seller", seller);
                target = "seller_index.jsp";
            }
        }
        if (flag) {
            response.sendRedirect(target);
        } else {
            out.print("手机号或密码不正确，3秒钟后请重新登录");
            response.setHeader("refresh", "3;URL=html/welcome.html");
            out.print("若没有跳转请点击<a href=html/welcome.html>这里</a>");
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
