package com.nefu.servlet;

import com.nefu.bean.Seller;
import com.nefu.bean.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        boolean isuser = request.getParameter("isuser").equals("1");
        request.getSession().setAttribute("isuser", isuser);
        boolean flag;
        if (isuser) {
            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            user.setNick_name(request.getParameter("name"));
            flag = user.register();
        } else {
            Seller seller = new Seller();
            seller.setPhone(phone);
            seller.setPassword(password);
            seller.setShop_name(request.getParameter("name"));
            flag = seller.register();
        }
        String str;
        if (flag) {
            str = "注册成功，3秒钟后跳转至登录界面";
        } else {
            str = "该手机号已注册，请直接登录，3秒钟后跳转";
        }
        out.print(str);
        response.setHeader("refresh", "3;URL=html/welcome.html");
        out.print("若没有跳转请点击<a href=html/welcome.html>这里</a>");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
