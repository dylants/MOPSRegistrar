<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" />
</head>

<body>
    <!-- header -->
    <div id="header">
        <strong>MOPS Registration</strong>
        <div id="toc">
            <div id="toc_items">
                <span id="toc_item"> <a href="home">Home</a> </span>
                <span id="toc_item"> <a href="register">Register</a> </span>
                <span id="toc_item"> <a href="list">Listing</a> </span>
                <span id="toc_item"> <a href="#">Search</a> </span>
                <span id="toc_item"> <a href="#">Contact Us</a> </span>
            </div>
        </div>
    </div>
    <!--end header -->

    <!-- main -->
    <div id="main">
        <tiles:insertAttribute name="body"/>
    </div>
    <!-- end main -->

    <!-- footer -->
    <div id="footer">
        <div id="left_footer">
        </div>
        <div id="right_footer">
        </div>
    </div>
    <!-- end footer -->

</body>
</html>