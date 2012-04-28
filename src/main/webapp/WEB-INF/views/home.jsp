<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userRoot" value="${contextPath}/page/user" />

<div id="home" class="curved_narrow">

    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <!-- Content for users who are not logged in -->
        <h1>
            <strong>Welcome to MOPS registration!</strong>
        </h1>
        <div id="information">
            <p>If you're an existing user, please login by clicking the link below. Otherwise please register as a new user.</p>
        </div>

        <div id="home_links">
            <a href="${contextPath}/page/login">Login as an Existing User</a> <a href="${userRoot}/register">New
                User Registration</a>
        </div>
    </sec:authorize>
    <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
        <!-- Content for users who are logged in -->
        <h1>
            <strong>Welcome back ${firstName}!</strong>
        </h1>
    </sec:authorize>
</div>
