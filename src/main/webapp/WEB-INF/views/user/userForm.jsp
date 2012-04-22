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
					</tr>
                    <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="firstName.errors" class="error">${firstNameErrors}</span></td>
                    </tr>
					<tr>
						<td>Last Name:</td>
						<td><form:input path="lastName" cssClass="longText" /></td>
					</tr>
                    <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="lastName" class="error">${lastNameErrors}</span></td>
                    </tr>
					<tr>
						<td>Email Address:</td>
						<td><form:input path="emailAddress" cssClass="longText" /></td>
					</tr>
                    <c:set var="emailAddressErrors"><form:errors path="emailAddress"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="emailAddress" class="error">${emailAddressErrors}</span></td>
                    </tr>
					<tr>
						<td>Password:</td>
						<td><form:password path="clearTextPassword" cssClass="longText" /></td>
					</tr>
                    <c:set var="clearTextPasswordErrors"><form:errors path="clearTextPassword"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="clearTextPassword" class="error">${clearTextPasswordErrors}</span></td>
                    </tr>
	                <tr>
	                    <td>Confirm Password:</td>
	                    <td><form:password path="clearTextConfirmPassword" cssClass="longText" /></td>
	                </tr>
                    <c:set var="clearTextConfirmPasswordErrors"><form:errors path="clearTextConfirmPassword"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="clearTextConfirmPassword" class="error">${clearTextConfirmPasswordErrors}</span></td>
                    </tr>
					<tr>
						<td>Home Address:</td>
						<td><form:input	path="registrationInformation.address.homeAddress1"	cssClass="longText" /></td>
					</tr>
                    <c:set var="homeAddress1Errors"><form:errors path="registrationInformation.address.homeAddress1"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="homeAddress1.errors" class="error">${homeAddress1Errors}</span></td>
                    </tr>
					<tr>
						<td></td>
						<td><form:input	path="registrationInformation.address.homeAddress2"	cssClass="longText" /></td>
					</tr>
                    <c:set var="homeAddress2Errors"><form:errors path="registrationInformation.address.homeAddress2"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="homeAddress2.errors" class="error">${homeAddress2Errors}</span></td>
                    </tr>
					<tr>
						<td>City:</td>
						<td><form:input path="registrationInformation.address.city"	cssClass="longText" /></td>
					</tr>
                    <c:set var="cityErrors"><form:errors path="registrationInformation.address.city"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="city.errors" class="error">${city}</span></td>
                    </tr>
					<tr>
						<td>State:</td>
						<td><form:input path="registrationInformation.address.state" cssClass="longText" /></td>
					</tr>
                    <c:set var="stateErrors"><form:errors path="registrationInformation.address.state"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="state.errors" class="error">${state}</span></td>
                    </tr>
					<tr>
						<td>Zip Code:</td>
						<td><form:input path="registrationInformation.address.zipCode" cssClass="longText" /></td>
					</tr>
                    <c:set var="zipCoderrors"><form:errors path="registrationInformation.address.zipCode"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="zipCode.errors" class="error">${zipCode}</span></td>
                    </tr>
                    <tr>
                        <td>Phone Number:</td>
                        <td><form:input path="registrationInformation.phoneNumber" cssClass="longText" /></td>
                    </tr>
                    <c:set var="phoneNumberErrors"><form:errors path="registrationInformation.phoneNumber"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="phoneNumber.errors" class="error">${phoneNumberErrors}</span></td>
                    </tr>
                    <tr>
                        <td>Date of Birth:<br/>(MM/DD/YYYY)</td>
                        <td><form:input path="registrationInformation.dateOfBirth" cssClass="longText" /></td>
                    </tr>
                    <c:set var="dateOfBirthErrors"><form:errors path="registrationInformation.dateOfBirth"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="dateOfBirth.errors" class="error">${dateOfBirthErrors}</span></td>
                    </tr>
				</table>
                <div class="submitButtons">
                    <span class="submitButton"><input type="submit" value="${submitButtonText}"/></span>
                    <span class="submitButton"><a href="${homeUrl}"> <input type="button" value="Cancel"/></a></span>
                </div>
			</form:form>
		</div>
	
	</div>
