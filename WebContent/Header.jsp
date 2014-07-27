<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<div class="header">
    <div id="heading">
        <h1>Southwind Trading Company</h1>
    </div>
    <div id="search">
        
    </div>
    <div class="menu">    
        <ul>
            <li id="navIndex">
                <a href="<c:url value='/index.jsp' />" target="_self">Home</a>
            </li>
            <li id="navProducts">
                <a href="<c:url value='/Product.action' />" target="_self">Products</a>
            </li>
            <li id="navCart">
                <a href="<c:url value='/Cart.action' />" target="_self">Shopping Cart</a>
            </li>
            <li id="navLogin">
                <a href="<c:url value='/login.jsp' />" target="_self">Login</a>
            </li>
            <li id="navAccount">
                <stripes:link beanclass="action.ProfileActionBean" event="AccountDetail">
                	My Account
                </stripes:link>
            </li>
        </ul>
    </div>
</div> <!-- /#header -->