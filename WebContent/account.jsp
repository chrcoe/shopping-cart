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
<title>My Account</title>
</head>
<body>
	<div id="container">
		<jsp:include page="Header.jsp" />
		<div id="content">
			<h2>My Account</h2>
			<div class="accountContainer">Name: ${actionBean.userName}</div>
			<br /> <a href="orderhistory.jsp"></a>
			<stripes:link beanclass="action.ProfileActionBean"
				event="OrderHistory">
                	see order history
                </stripes:link>
		</div>
		<jsp:include page="Footer.jsp" />
	</div>
</body>
</html>