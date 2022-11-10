package com.nefu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isuser = (boolean) request.getSession().getAttribute("isuser");
        request.getSession().removeAttribute("isuser");
        String type_str = isuser ? "user" : "seller";
        request.getSession().removeAttribute(type_str);
        response.sendRedirect("html/welcome.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
