<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
    <c:when test="${isNew}">
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
                <form:errors cssClass="errorblock" element="div" />
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
						<td><form:password path="clearTextPassword" cssClass="longText" /></td>
						<td><form:errors path="clearTextPassword" cssClass="error" /></td>
					</tr>
	                <tr>
	                    <td>Confirm Password:</td>
	                    <td><form:password path="clearTextConfirmPassword" cssClass="longText" /></td>
	                    <td><form:errors path="clearTextConfirmPassword" cssClass="error" /></td>
	                </tr>
					<tr>
						<td>Home Address:</td>
						<td><form:input	path="registrationInformation.address.homeAddress1"	cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.address.homeAddress1" cssClass="error" /></td>
					</tr>
					<tr>
						<td></td>
						<td><form:input	path="registrationInformation.address.homeAddress2"	cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.address.homeAddress2" cssClass="error" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><form:input path="registrationInformation.address.city"	cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.address.city" cssClass="error" /></td>
					</tr>
					<tr>
						<td>State:</td>
						<td><form:input path="registrationInformation.address.state" cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.address.state" cssClass="error" /></td>
					</tr>
					<tr>
						<td>Zip Code:</td>
						<td><form:input path="registrationInformation.address.zipCode" cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.address.zipCode" cssClass="error" /></td>
					</tr>
                    <tr>
                        <td>Phone Number:</td>
                        <td><form:input path="registrationInformation.phoneNumber" cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.phoneNumber" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td>Date of Birth:<br/>(MM/DD/YYYY)</td>
                        <td><form:input path="registrationInformation.dateOfBirth" cssClass="longText" /></td>
                        <td><form:errors path="registrationInformation.dateOfBirth" cssClass="error" /></td>
                    </tr>
				</table>
                <div class="submitButtons">
                    <span class="submitButton"><input type="submit" value="${submitButtonText}"/></span>
                    <span class="submitButton"><a href="${homeUrl}"> <input type="button" value="Cancel"/></a></span>
                </div>
			</form:form>
		</div>
	
	</div>
