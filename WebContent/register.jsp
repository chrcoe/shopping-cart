<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
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
              <div class="form">
                <stripes:form beanclass="action.ProfileActionBean" focus="">
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
	                    <stripes:submit  class="shared_row" name="Register" value="Register"/>
	                </div>
                </stripes:form>
              </div>
        </div>
    </div>
</body>
</html>