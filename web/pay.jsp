<%@ page import="com.nefu.bean.User" %>
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