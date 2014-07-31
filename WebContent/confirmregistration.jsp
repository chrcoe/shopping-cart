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
<script src="./js/jquery1.11.js" type="text/javascript"></script>
<title>Registration Confirmation</title>
</head>
<body>
    <div class="cbForm">
        <div id="content" >
            <h2>Registration Confirmation</h2>
              <div>
                <p style="text-align:center">Thank you for registering.</p>
                <br><br>
                <a id="close" style="align:center" href="/">Close Window</a>
              </div>
        </div>
    </div>
    
    <script type="text/javascript">
    $(document).ready(function() {
        $("#close").click(function(event){
        	event.preventDefault();
        	window.top.location.href = 'index.jsp';
        });
    });
    
    </script>
</body>
</html>