<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
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
			<c:if test="${actionBean.error}">
			<div>Cart Error: ${actionBean.errorMessage}</div>
			</c:if>

			<div class="cart_container">
				<c:forEach items="${actionBean.userCart.items}" var="item">
					<div class="cart_name">${item.product.productName}</div>
					<div class="cart_quant">Quantity: ${item.quantity}</div>
					<div class="cart_item_price">Unit Price:
						$${item.product.unitPrice}</div>
					<div class="cart_item_total">Item Total: $${item.linePrice}</div>
					<stripes:link beanclass="action.CartActionBean" event="RemoveFromCart">Remove</stripes:link>
					<hr />
				</c:forEach>

				<div class="cart_total">Sub Total:
					${actionBean.userCart.cartTotal}</div>
				<div class="cart_total">Shipping:
					${actionBean.userCart.shippingCost}</div>
				<div class="cart_total">Total:
					${actionBean.userCart.shippingCost+actionBean.userCart.cartTotal}</div>

			</div>
			<stripes:link beanclass="action.CartActionBean" event="CheckOut">Check Out</stripes:link>
		</div>
		<jsp:include page="Footer.jsp" />
	</div>
</body>
</html>