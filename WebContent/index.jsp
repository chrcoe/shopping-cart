<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>Our Store</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Welcome to Our Store</h2>
            <div id="home_container">
                <div class="home_box" id="home_intro"><p>Southwind Trading Company has been supplying the finest merchandise since 1738. We stock only the highest quality goods from all over the world. Whatever you need to buy, we need to sell. We now have a shopping cart on our site that allows you to browse and purchase products from the comfort of your own home. We hope you enjoy a hassle free shopping experience, and if there is anyway we can assist you, please feel free to contact us.</p></div>
                <div class="home_box" id="home_image"><img src="./images/store_image.png"/></div>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>