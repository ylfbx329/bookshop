<%@ page import="com.nefu.bean.User" %>
<%@ page import="com.nefu.bean.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nefu.bean.Book" %>
<%@ page import="javafx.util.Pair" %><%--
  Created by IntelliJ IDEA.
  User: kangl
  Date: 2022/11/5
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>购物车</title>
	<script src="js/jquery-3.6.1.js"></script>
	<script src="js/ajax_setting.js"></script>
	<script src="js/table.js"></script>
	<script src="js/delete_order.js"></script>
	<link rel="stylesheet" href="css/book_table.css"/>
</head>
<body>
<%
	User user = (User) session.getAttribute("user");
%>
<div>
	<span>你好，<%=user.getNick_name()%></span>
	<span hidden id="user_id"><%=user.getUser_id()%></span>
</div>
<%
	List<Pair<Order, Book>> orders = Order.queryOrder(user.getUser_id());
	if (orders == null) {
		out.print("购物车内暂无商品，请前去选购吧！");
		response.sendRedirect("user_index.jsp");
	} else {
%>
<table>
	<tr>
		<th>封面</th>
		<th>书名</th>
		<th>作者</th>
		<th>出版社</th>
		<th>店铺</th>
		<th>单价</th>
		<th>数量</th>
		<th>总价</th>
		<th>删除</th>
	</tr>
	<%
		for (Pair<Order, Book> order : orders) {
	%>
	<tr>
		<td>
			<%=order.getValue().getImg()%>
		</td>
		<td>
			<%=order.getValue().getBook_name()%>
		</td>
		<td>
			<%=order.getValue().getAuthor()%>
		</td>
		<td>
			<%=order.getValue().getPress()%>
		</td>
		<td>
			<%=order.getValue().getShop_name()%>
		</td>
		<td>
			<%=order.getValue().getPrice()%>
		</td>
		<td>
			<%=order.getKey().getOrder_mount()%>
		</td>
		<td>
			<%=order.getKey().getPrice()%>
		</td>
		<td>
			<button class="delete" type="button" value="<%=order.getKey().getOrder_id()%>">删除</button>
		</td>
	</tr>
	<%
			}
		}
	%>
</table>
<button name="buy" onclick="buy_book()">结算</button>
</body>
</html>
