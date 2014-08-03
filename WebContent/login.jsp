<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<link rel="stylesheet" href="./css/colorbox.css" type="text/css" media="screen" />
<script src="./js/jquery1.11.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/jquery.colorbox-min.js"></script>
<title>Login</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Login</h2>
            
            <c:if test="${actionBean.error}">
			<div>Error: ${actionBean.errorMessage}</div>
			</c:if>
			
            <stripes:form beanclass="action.ProfileActionBean" focus="">
            <div class="form">             
                <div class="single_row">
                    <label for="username">Username: </label>
                    <input type="text" id="username" name="userName">
                </div>
                <div class="single_row">
                    <label for="password">Password: </label>
                    <input type="password" id="password">
                </div>
                <div class="login_buttons">
                	<stripes:submit  class="shared_row" name="LogIn" value="Log In" style="width:150px"/>
                	
                </div>
            </div>
            </stripes:form>
            <div style="margin:auto; width:75px" class="shared_row">
                <a class="regCb" href="register.jsp">Register</a>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
    <script>
    $(document).ready(function() {
        $(".regCb").colorbox({iframe: true, innerWidth:500, innerHeight:650});
        $("#loginBtn").click(function(){
        	
        });
    });
    </script>
</body>
</html>