<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css" type="text/css">
<title>Login</title>
</head>
<body>
    <div id="container">
        <jsp:include page="Header.jsp" />
        <div id="content">
            <h2>Login</h2>
            <div id="column" style="float:left;margin:5;width:50%">
            this form currently does nothing
              <FORM action="login.jsp" method="post">
                <label for="username">Username: </label>
                  <input type="text" id="username"><BR>
                <label for="password">Password: </label>
                  <input type="password" id="password"><BR>
                <input type="submit" value="Login">
              </FORM>
            </div>
            <div id="column" style="float:left;margin:5;width:50%">
              <a href='register.jsp'>register</a>
            </div>
        </div>
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>