<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
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
            <c:forEach items="${actionBean.products}" var="product">
                <div class="product_container">
                    <div class="product_image">
                        <img src="${product.imagePath}"/>
                    </div>
                    <div class="product_description">
                        <div class="product_name">${product.productName}</div>
                        <div class="product_category product_row2">Category: ${product.categoryName}</div>
                        <div class="product_price product_row2">Price: $${product.unitPrice}</div>
                    </div>
                    <stripes:form beanclass="action.CartActionBean" focus="">
                    <div>
                        <label for="quantity">Quantity: &nbsp;</label>
                        <input type="text" id="quantity" name="quantity" style="width:20px; height:20px; margin-right:10px" default="1"/>
                        <input type="hidden" name="itemId" value="${product.productID}"/>
                    </div>
                    <div class="product_link">
                        <stripes:submit name="AddToCart">Add to Cart</stripes:submit>
                    </div>
                    </stripes:form>
                    <div class="product_link">
                        <stripes:link beanclass="action.ProductActionBean" event="showDetail" title="Stock: ${product.unitsInStock}">
                            <stripes:param name="itemId" value="${product.productID}"/>
                            Details
                        </stripes:link>
                    </div>
                </div>
            </c:forEach>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>