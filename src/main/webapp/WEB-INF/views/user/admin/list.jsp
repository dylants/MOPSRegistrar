<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- no need to supply context root, it's automatically used in URL generation below -->
<c:set var="userRoot" value="/page/user"/>
<c:set var="adminRoot" value="${userRoot}/admin"/>

    <div id="admin_list">
    	<div>
    
    		<c:if test="${empty users}">
    			<p>&nbsp;</p>
    		</c:if>
    
    		<c:if test="${not empty users}">
    			<table id="admin_list_table">
    				<tr>
    					<th>Email Address</th>
    					<th>First Name</th>
    					<th>Last Name</th>
                        <th>Home Address 1</th>
                        <th>Home Address 2</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Zip Code</th>
    				</tr>
    				<c:forEach var="user" items="${users}">
                        <c:url value="${adminRoot}/edit/${user.entityId}" var="editUrl"/>
    					<tr>
    						<td><a href="${editUrl}">${user.emailAddress}</a></td>
    						<td>${user.firstName}</td>
    						<td>${user.lastName}</td>
                            <td>${user.registrationInformation.address.homeAddress1}</td>
                            <td>${user.registrationInformation.address.homeAddress2}</td>
                            <td>${user.registrationInformation.address.city}</td>
                            <td>${user.registrationInformation.address.state}</td>
                            <td>${user.registrationInformation.address.zipCode}</td>
    					</tr>
    				</c:forEach>
    			</table>
    		</c:if>
    
    	</div>

        <a href="home">Return to Registration Home</a>

    </div>
