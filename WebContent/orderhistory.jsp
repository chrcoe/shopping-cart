<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>My Order History</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>My Order History</h2>
            <ul>
            <c:set var="orderCount" scope="session" value="${1}"/>
            <c:forEach items="${actionBean.orders}" var="order">
                <li>Order ${orderCount} - Status: ${order.status}</li>
                <c:set var="orderCount" scope="session" value="${orderCount+1}"/>
            </c:forEach>
            </ul>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>