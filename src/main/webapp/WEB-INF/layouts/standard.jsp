<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" />
</head>

<body>
    <!-- header -->
    <div id="menu">
        <div id="menu_list">
            <a href="home">Home</a>
            <a href="registerUser">Register</a> 
            <a href="listUsers">Listing</a> 
            <a href="#">Search</a>
            <a href="#">Contact Us</a>
        </div>
    </div>
    <!--end header -->

    <!-- main -->
    <tiles:insertAttribute name="body"/>
    <!-- end main -->

    <!-- footer -->
    <div id="footer">
        <div id="left_footer">
            MOPS Registrar
        </div>
        <div id="right_footer">
            &copy; Copyright 2009 <b>Your Website</b>
        </div>
    </div>
    <!-- end footer -->

</body>
</html>