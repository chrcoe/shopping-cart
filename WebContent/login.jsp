<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            
            <p>this form currently does nothing</p>
            <div class="form">             
                <div class="single_row">
                    <label for="username">Username: </label>
                    <input type="text" id="username">
                </div>
                <div class="single_row">
                    <label for="password">Password: </label>
                    <input type="password" id="password">
                </div>
                <div class="login_buttons">
                    <div id="loginBtn" class="button shared_row">Login</div>
                    <a href="register.jsp" class="regCb"><div class="button shared_row">Register</div></a>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
    <script>
    $(document).ready(function() {
        $(".regCb").colorbox({iframe: true, innerWidth:500, innerHeight:500});
        $("#loginBtn").click(function(){
        	
        });
    });
    </script>
</body>
</html>