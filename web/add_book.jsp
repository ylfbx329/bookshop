<%@ page import="com.nefu.bean.Seller" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>上架图书</title>
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
<a href="seller_index.jsp">返回店铺</a>
<h1>请填写图书基本信息</h1>
<form action="AddBookServlet" method="post" id="form">
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
		<input type="file" name="img" id="img">
	</div>
	<div>
		<input type="submit" value="保存更改">
	</div>
</form>
</body>
</html>
