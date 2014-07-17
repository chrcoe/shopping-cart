<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<link rel="stylesheet" href="./css/jquery-ui-1.10.4.css">
<title>Register</title>
</head>
<body>
    <div class="cbForm">
        <div id="content" >
            <h2>Register</h2>
            this form currently does nothing
              <div class="form">
                <div class="single_row">
                    <label for="username">Username: </label>
                    <input type="text" id="username"><BR>
                </div>
                <div class="single_row">
                    <label for="password">Password: </label>
                    <input type="password" id="password"><BR>
                </div>
                <div class="single_row">
                    <label for="email">Email: &nbsp; &nbsp; </label>
                    <input type="text" id="email"><BR>
                </div>
                <div class="single_row">
                    <label for="firstname">First Name: </label>
                    <input type="text" id="firstname"><BR>
                </div>
                <div class="single_row">
                    <label for="lastname">Last Name: </label>
                    <input type="text" id="lastname"><BR>
                </div>
                <div class="single_row">
                    <div class="button shared_row">Register</div>
                </div>
              </div>
        </div>
    </div>
</body>
</html>