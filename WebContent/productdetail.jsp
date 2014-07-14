<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <div class="detail_image detail_row1"><img src="./images/coming_soon_large.png"/></div>
                <div class="detail_product detail_row1">
                    <div class="detail_name">${actionBean.item.productName}</div>
                    <div class="detail_desc">This is an excellent product for demonstrating your website. If you need to show exactly what your site will look like when you actually have some content, then you can't go wrong with one of these demo products.</div>
                </div>
                <div class="detail_quant detail_row2"><b>Number Available:</b>&nbsp; &nbsp; ${actionBean.item.unitsInStock}</div>
                <div class="detail_price detail_row2"><b>Price:</b>&nbsp; &nbsp; $ ${actionBean.item.unitPrice}</div>
                <div class="detail_add">
                    <div class="button">Add To Cart</div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>