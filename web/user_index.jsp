<%@ page import="com.nefu.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nefu.bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: kangl
  Date: 2022/11/3
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>商品选择</title>
	<script src="js/jquery-3.6.1.js"></script>
	<script src="js/ajax_setting.js"></script>
	<script src="js/table.js"></script>
	<script src="js/buy_book.js"></script>
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
<a href="shopping_cart.jsp">查看购物车</a>
<%
	List<Book> books = Book.queryBook(null);
	if (books.size() == 0)
		out.print("暂无商品在售，请下次光临！");
	else {
%>
<table>
	<tr>
		<th>编号</th>
		<th>封面</th>
		<th>书名</th>
		<th>作者</th>
		<th>出版社</th>
		<th>出版日期</th>
		<th>ISBN</th>
		<th>店铺</th>
		<th>现货</th>
		<th>已售</th>
		<th>售价</th>
		<th>已选</th>
	</tr>
	<%
		for (int i = 0; i < books.size(); i++) {
	%>
	<tr>
		<td>
			<%=i + 1%>
		</td>
		<td class="book_id" hidden>
			<%=books.get(i).getBook_id()%>
		</td>
		<td class="img">
			<%=books.get(i).getImg()%>
		</td>
		<td class="book_name">
			<%=books.get(i).getBook_name()%>
		</td>
		<td class="author">
			<%=books.get(i).getAuthor()%>
		</td>
		<td class="press">
			<%=books.get(i).getPress()%>
		</td>
		<td class="pub_date">
			<%=books.get(i).getPub_date()%>
		</td>
		<td class="isbn">
			<%=books.get(i).getIsbn()%>
		</td>
		<td class="shop_name">
			<%=books.get(i).getShop_name()%>
		</td>
		<td class="store_mount">
			<%=books.get(i).getStore_mount()%>
		</td>
		<td class="deal_mount">
			<%=books.get(i).getDeal_mount()%>
		</td>
		<td class="price">
			<%=books.get(i).getPrice()%>
		</td>
		<td>
            <span class="sub">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="feather feather-minus-circle">
                    <circle cx="12" cy="12" r="10"></circle>
                    <line x1="8" y1="12" x2="16" y2="12"></line>
                </svg>
            </span>
			<span class="book_num">0</span>
			<span class="add">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="feather feather-plus-circle">
                    <circle cx="12" cy="12" r="10"></circle>
                    <line x1="12" y1="8" x2="12" y2="16"></line>
                    <line x1="8" y1="12" x2="16" y2="12"></line>
                </svg>
            </span>
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
