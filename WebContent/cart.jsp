<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>Shopping Cart</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Shopping Cart</h2>
            <div class="cart_container">
                <c:forEach items="${requestScope.cartItems}" var="item">
                    <div class="cart_name"></div>
                    <div class="cart_quant"></div>
                    <div class="cart_item_price"></div>
                    <div class="cart_item_total"></div>
                </c:forEach>
                <div class="cart_total"></div>
            </div>
            <a href="checkout.jsp">Check Out</a>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>