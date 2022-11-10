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
	<a href="LogoutServlet">退出登录</a>
</div>
<a href="user_index.jsp">返回商品购买页</a>
<%
	List<Pair<Order, Book>> orders = Order.queryOrder(user.getUser_id());
	if (orders.size() == 0) {
		out.print("购物车内暂无商品，请前去选购吧！");
	} else {
%>
<table>
	<tr>
		<th>序号</th>
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
		for (int i = 0; i < orders.size(); i++) {
	%>
	<tr>
		<td>
			<%=i + 1%>
		</td>
		<td>
			<%=orders.get(i).getValue().getImg()%>
		</td>
		<td>
			<%=orders.get(i).getValue().getBook_name()%>
		</td>
		<td>
			<%=orders.get(i).getValue().getAuthor()%>
		</td>
		<td>
			<%=orders.get(i).getValue().getPress()%>
		</td>
		<td>
			<%=orders.get(i).getValue().getShop_name()%>
		</td>
		<td>
			<%=orders.get(i).getValue().getPrice()%>
		</td>
		<td>
			<%=orders.get(i).getKey().getOrder_mount()%>
		</td>
		<td>
			<%=orders.get(i).getKey().getPrice()%>
		</td>
		<td>
			<button class="delete" type="button" value="<%=orders.get(i).getKey().getOrder_id()%>">删除</button>
		</td>
	</tr>
	<%
		}
	%>
</table>
<button class="pay" type="button">支付</button>
<%
	}
%>
</body>
</html>
