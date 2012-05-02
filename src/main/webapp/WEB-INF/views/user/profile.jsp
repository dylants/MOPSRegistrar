<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userProfileRoot" value="${contextPath}/page/user/profile" />

<div id="user_profile" class="curved_narrow">

        <h1>
            <strong>Profile for ${user.registrationInformation.firstName} ${user.registrationInformation.lastName}</strong>
        </h1>

        <p>Click on the link below to update your personal profile or MOPPETS related information</p>

        <div id="user_profile_links">
            <a href="${userProfileRoot}/registrationInformation">Edit Registration Information</a> <a href="#">Edit MOPPETS Information</a>
        </div>
</div>
