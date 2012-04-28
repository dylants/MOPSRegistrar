<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userProfileRoot" value="${contextPath}/page/user/profile" />

<div id="user_profile" class="curved_narrow">

        <h1>
            <strong>Profile for ${user.firstName} ${user.lastName}</strong>
        </h1>

        <p>Click on the link below to update your personal profile or MOPETTS related information</p>

        <div id="user_profile_links">
            <a href="${userProfileRoot}/editRegistrationInformation">Edit Registration Information</a> <a href="#">Edit MOPETTS Information</a>
        </div>
</div>
