<%@ page import="com.nefu.bean.Seller" %><%--
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
    <script src="js/table.js"></script>
    <script src="js/buy_book.js"></script>
    <link rel="stylesheet" href="css/book_table.css"/>
</head>
<body>
<%
    Seller seller = (Seller) session.getAttribute("seller");
%>
<div>
    <span><%=seller.getSeller_id()%>，欢迎您</span>
</div>

</body>
</html>
