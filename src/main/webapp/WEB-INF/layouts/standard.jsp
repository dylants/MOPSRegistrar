<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userRoot" value="${contextPath}/page/user"/>
<c:set var="adminRoot" value="${contextPath}/page/admin"/>

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
                <span id="toc_item"> <a href="${contextPath}/page/home">Home</a> </span>
                <span id="toc_item"> <a href="${userRoot}/register">Register</a> </span>
                <span id="toc_item"> <a href="#">Login</a> </span>
                <span id="toc_item"> <a href="${adminRoot}/home">Admin</a> </span>
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

</body>
</html>