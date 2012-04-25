<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="adminRoot" value="${contextPath}/page/admin"/>

    <div id="admin_list">

        <h1>List of Registered Users</h1>

		<c:if test="${empty users}">
			<p>&nbsp;</p>
		</c:if>

		<c:if test="${not empty users}">
			<table id="admin_list_table" class="sortable">
				<tr class="column_names">
					<th><a href="#">Email Address</a></th>
					<th><a href="#">First Name</a></th>
                    <th><a href="#">M.I.</a></th>
					<th><a href="#">Last Name</a></th>
                    <th><a href="#">Home Address</a></th>
                    <th><a href="#">City</a></th>
                    <th><a href="#">State</a></th>
                    <th><a href="#">Zip</a></th>
                    <th><a href="#">Daytime Number</a></th>
                    <th><a href="#">Evening Number</a></th>
                    <th><a href="#">Date of Birth</a></th>
                    <th><a href="#">Children</a></th>
				</tr>
				<c:forEach var="user" items="${users}" varStatus="loopStatus">
                    <c:url value="/page/admin/user/edit/${user.entityId}" var="editUrl"/>
					<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
						<td><a href="${editUrl}">${user.emailAddress}</a></td>
						<td>${user.firstName}</td>
                        <td>${user.middleInitial}</td>
						<td>${user.lastName}</td>
                        <td>${user.registrationInformation.address.homeAddress}</td>
                        <td>${user.registrationInformation.address.city}</td>
                        <td>${user.registrationInformation.address.state}</td>
                        <td>${user.registrationInformation.address.zipCode}</td>
                        <td>${user.registrationInformation.daytimePhone}</td>
                        <td>${user.registrationInformation.eveningPhone}</td>
                        <td><spring:eval expression="user.registrationInformation.dateOfBirth" /></td>
                        <td>${user.registrationInformation.children}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
    
        <br/>
        <a href="${adminRoot}/home">Return to Admin Home</a>

    </div>

    <script src="<c:url value="/resources/js/sorttable.js" />"></script>