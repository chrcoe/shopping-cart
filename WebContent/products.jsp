<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>Products</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Products</h2>
            <div class="product_container">
                <div class="product_image">
                    <img src="./images/coming_soon_image.png"/>
                </div>
                <div class="product_description">
                    <div class="product_name">"A Demo Product"</div>
                    <div class="product_category product_row2">Category: Miscellaneous</div>
                    <div class="product_price product_row2">Price: $130.95</div>
                    <div class="product_available">Number Available: 5</div>
                </div>
                <div class="add_product">Add to Cart</div>
                <div class="detail_link">
                    <a href='productdetail.jsp'>Details</a>
                </div>
            </div>
            <c:forEach items="${actionBean.allProducts}" var="product">
                <div class="product_container">
                    <div class="product_image">
                        
                    </div>
                    <div class="product_description">
                        <div class="product_name">${product.productName}</div>
                        <div class="product_category">Category: ${product.categoryName}</div>
                        <div class="product_price">Price: $${product.unitPrice}</div>
                    </div>
                    <div class="detail_link">
                        <a href='Product.action?getProduct=&itemId=${product.productID}'>Details</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>