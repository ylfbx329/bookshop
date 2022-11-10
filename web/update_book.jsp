<%@ page import="com.nefu.bean.Book" %>
<%@ page import="com.nefu.bean.Seller" %><%--
  Created by IntelliJ IDEA.
  User: kangl
  Date: 2022/11/10
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<script src="js/jquery-3.6.1.js"></script>
	<script src="js/ajax_setting.js"></script>
	<script src="js/update_book.js"></script>
	<title>图书信息</title>
</head>
<body>
<%
	Seller seller = (Seller) session.getAttribute("seller");
%>
<div>
	<span><%=seller.getShop_name()%>，欢迎您</span>
	<span hidden id="seller_id"><%=seller.getSeller_id()%></span>
	<a href="LogoutServlet">退出登录</a>
</div>
<h1>请修改图书基本信息</h1>
<form action="UpdateBookServlet" method="post" id="form">
	<div>
		<label for="isbn">ISBN</label>
		<input type="text" name="isbn" id="isbn">
	</div>
	<div>
		<label for="book_name">书名</label>
		<input type="text" name="book_name" id="book_name">
	</div>
	<div>
		<label for="author">作者</label>
		<input type="text" name="author" id="author">
	</div>
	<div>
		<label for="press">出版社</label>
		<input type="text" name="press" id="press">
	</div>
	<div>
		<label for="pub_date">出版日期</label>
		<input type="date" name="pub_date" id="pub_date">
	</div>
	<div>
		<label for="store_mount">存货量</label>
		<input type="number" name="store_mount" id="store_mount">
	</div>
	<div>
		<label for="price">定价</label>
		<input type="text" name="price" id="price">
	</div>
	<div>
		<label for="img">封面</label>
		<input type="file" alt="图片加载失败" name="img" id="img">
	</div>
	<div>
		<input type="submit" value="保存更改">
	</div>
</form>
</body>
</html>
