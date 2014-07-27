<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>Product Detail</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Product Detail</h2>
            <div class="detail_container">
                <div class="detail_image detail_row1"><img src="${actionBean.item.imagePath}"/></div>
                <div class="detail_product detail_row1">
                    <div class="detail_name">${actionBean.item.productName}</div>
                    <div class="detail_desc">${actionBean.item.description}</div>
                </div>
                <div class="detail_price detail_row2"><b>Price:</b>&nbsp; &nbsp; $ ${actionBean.item.unitPrice}</div>
                <stripes:form class="detail_add" beanclass="action.CartActionBean">
                    <label for="quantity">Quantity: &nbsp;</label>
                    <input type="text" id="quantity" name="quantity" style="width:20px; height:20px; margin-right:10px" default="1"/>
                    
                    <stripes:submit name="AddToCart" value="Add to Cart" style="width:150px" />
                </stripes:form>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>