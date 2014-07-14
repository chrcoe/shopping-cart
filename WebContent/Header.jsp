<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div id="heading">
        <h1>Southwind Trading Company</h1>
    </div>
    <div id="search">
        <form method="post" action="SearchForProducts.do">
            <input name="itemName" type="text" value="" class="txtfield" />
            <input type="submit" value="Search Products" class="" />
        </form>
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
                <a href="<c:url value='/cart.jsp' />" target="_self">Shopping Cart</a>
            </li>
            <li id="navLogin">
                <a href="<c:url value='/login.jsp' />" target="_self">Login</a>
            </li>
            <li id="navAccount">
                <a href="<c:url value='/account.jsp' />" target="_self">My Account</a>
            </li>
        </ul>
    </div>
</div> <!-- /#header -->