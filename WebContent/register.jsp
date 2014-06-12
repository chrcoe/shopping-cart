<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>Register</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Register</h2>
            this form currently does nothing
              <FORM action="register.jsp" method="post">
                <label for="username">Username: </label>
                  <input type="text" id="username"><BR>
                <label for="password">Password: </label>
                  <input type="password" id="password"><BR>
                <label for="email">Email: </label>
                  <input type="text" id="email"><BR>
                <label for="firstname">First Name: </label>
                  <input type="text" id="firstname"><BR>
                <label for="lastname">Last Name: </label>
                  <input type="text" id="lastname"><BR>
                <input type="submit" value="Register">
              </FORM>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>