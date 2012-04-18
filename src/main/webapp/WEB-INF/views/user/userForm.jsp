<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
    <c:when test="${user['new']}">
        <c:set var="method" value="post"/>
    </c:when>
    <c:otherwise>
        <c:set var="method" value="put"/>
    </c:otherwise>
</c:choose>

	<div id="userForm">
	
		<h1>${heading}</h1>
	
		<div id="user_form">
			<form:form modelAttribute="user" method="${method}">
				<table>
					<tr>
						<td>First Name:</td>
						<td><form:input path="firstName" cssClass="longText" /></td>
						<td><form:errors path="firstName" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Last Name:</td>
						<td><form:input path="lastName" cssClass="longText" /></td>
						<td><form:errors path="lastName" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Email Address:</td>
						<td><form:input path="emailAddress" cssClass="longText" /></td>
						<td><form:errors path="emailAddress" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:password path="password" cssClass="longText" /></td>
						<td><form:errors path="password" cssClass="error" /></td>
					</tr>
	                <tr>
	                    <td>Confirm Password:</td>
	                    <td><form:password path="confirmPassword" cssClass="longText" /></td>
	                    <td><form:errors path="confirmPassword" cssClass="error" /></td>
	                </tr>
					<tr>
						<td>Home Address:</td>
						<td><form:input	path="registrationInformation.address.homeAddress1"	cssClass="longText" /></td>
					</tr>
					<tr>
						<td></td>
						<td><form:input	path="registrationInformation.address.homeAddress2"	cssClass="longText" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><form:input path="registrationInformation.address.city"	cssClass="longText" /></td>
					</tr>
					<tr>
						<td>State:</td>
						<td><form:input path="registrationInformation.address.state" cssClass="longText" /></td>
					</tr>
					<tr>
						<td>Zip Code:</td>
						<td><form:input path="registrationInformation.address.zipCode" cssClass="longText" /></td>
					</tr>
					<tr>
						<td class="submitButton"><input type="submit" value="${submitButtonText}" /></td>
						<td class="cancelButton"><a href="home"> <input type="button" value="Cancel" /></a></td>
					</tr>
				</table>
			</form:form>
		</div>
	
	</div>
