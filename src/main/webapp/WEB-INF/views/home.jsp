<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userRoot" value="${contextPath}/page/user"/>

    <div id="home">
    
        <h1>
            <strong>Welcome to MOPS registration!</strong>
        </h1>
    
        <p>If you're an existing user, please login by clicking the link below.  Otherwise please register as a new user.</p>

        <div id="home_links">    
            <a href="${contextPath}/spring_security_login">Login as an Existing User</a>
            <a href="${userRoot}/register">New User Registration</a>
        </div>
    
    </div>
