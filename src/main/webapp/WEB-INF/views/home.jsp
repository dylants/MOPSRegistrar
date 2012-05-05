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
            <p>If you're an existing user, please login by clicking the link below. Otherwise please create an account.</p>
        </div>

        <div id="home_links">
            <a href="${contextPath}/page/login">Login as an Existing User</a> <a href="${userRoot}/createAccount">Create Account</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_REGISTERED_USER')">
        <!-- Content for MOPS users -->
        <h1>
            <strong>Welcome back ${firstName}!</strong>
        </h1>
        <div id="information">
            <p>To update or add information, please click on the profile link below.</p>
        </div>

        <div id="home_links">
            <a href="${userRoot}/profile">View your Profile</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
        <!-- Content for Admin users -->
        <h1>
            <strong>Welcome back ${firstName}!</strong>
        </h1>
    </sec:authorize>
</div>
