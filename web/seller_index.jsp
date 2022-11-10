<%@ page import="com.nefu.bean.Seller" %>
<%@ page import="com.nefu.bean.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kangl
  Date: 2022/11/3
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>店内商品</title>
    <script src="js/jquery-3.6.1.js"></script>
    <script src="js/ajax_setting.js"></script>
    <script src="js/update_book.js"></script>
    <script src="js/table.js"></script>
    <link rel="stylesheet" href="css/book_table.css"/>
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
<%
    List<Book> books = Book.queryBook(seller.getSeller_id());
    if (books.size() == 0)
        out.print("暂无商品在售，请尽快上架商品！");
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
        <th>现货</th>
        <th>已售</th>
        <th>售价</th>
        <th>修改商品信息</th>
    </tr>
    <%
        for (int i = 0; i < books.size(); i++) {
    %>
    <tr>
        <td>
            <%=i + 1%>
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
            <button class="update" type="button">修改</button>
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
