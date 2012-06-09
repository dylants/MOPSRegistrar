<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="adminRoot" value="${contextPath}/page/admin"/>

    <div id="admin_list">

        <h1>List of Registered MOPS Users</h1>

		<c:if test="${empty mopsUsers}">
			<p>&nbsp;</p>
		</c:if>

		<c:if test="${not empty mopsUsers}">
			<table id="admin_list_table" class="sortable">
				<tr class="column_names">
					<th><a href="#">User Name</a></th>
					<th><a href="#">First Name</a></th>
                    <th><a href="#">M.I.</a></th>
					<th><a href="#">Last Name</a></th>
                    <th><a href="#">Street Address</a></th>
                    <th><a href="#">City</a></th>
                    <th><a href="#">State</a></th>
                    <th><a href="#">Zip</a></th>
                    <th><a href="#">Home Phone Number</a></th>
                    <th><a href="#">Cell Phone Number</a></th>
                    <th><a href="#">Date of Birth</a></th>
                    <th><a href="#">MOPPETS</a></th>
				</tr>
				<c:forEach var="user" items="${mopsUsers}" varStatus="loopStatus">
					<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
						<td><a href="${adminRoot}/user/edit/${user.entityId}/registrationInformation">${user.username}</a></td>
						<td>${user.registrationInformation.firstName}</td>
                        <td>${user.registrationInformation.middleInitial}</td>
						<td>${user.registrationInformation.lastName}</td>
                        <td>${user.registrationInformation.address.streetAddress}</td>
                        <td>${user.registrationInformation.address.city}</td>
                        <td>${user.registrationInformation.address.state}</td>
                        <td>${user.registrationInformation.address.zipCode}</td>
                        <td>${user.registrationInformation.homePhoneNumber}</td>
                        <td>${user.registrationInformation.cellPhoneNumber}</td>
                        <td><spring:eval expression="user.registrationInformation.dateOfBirth" /></td>
                        <c:choose>
                            <c:when test="${not empty user.childrenEntityIds}">
                                <td><a href="${adminRoot}/user/${user.entityId}/child/list">List MOPPETS</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>None</td>
                            </c:otherwise>
                        </c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:if>
    
        <br/>
        <a href="${adminRoot}/home">Return to Admin Home</a>

    </div>

    <script src="<c:url value="/resources/js/sorttable.js" />"></script>