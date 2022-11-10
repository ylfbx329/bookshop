<%@ page import="com.nefu.bean.User" %>
<%@ page import="com.nefu.bean.Order" %><%--
  Created by IntelliJ IDEA.
  User: kangl
  Date: 2022/11/10
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>支付成功</title>
</head>
<body>
<%
	User user = (User) session.getAttribute("user");
%>
<div>
	<span>你好，<%=user.getNick_name()%></span>
	<span hidden id="user_id"><%=user.getUser_id()%></span>
	<a href="LogoutServlet">退出登录</a>
</div>
<div>
	支付成功，总计：<%=request.getParameter("price")%>元！
</div>
<a href="user_index.jsp">返回商店</a>
</body>
</html>