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

	<div id="userForm" class="curved_wide">
	
		<h1>${heading}</h1>
        
		<form:form modelAttribute="user" method="${method}">
            <form:errors cssClass="errorBlock" element="div" />
            <p>Please complete the form below so we can learn some basic information about you</p>
            <div id="nameAndAddress">
                <table id="user_form_table">
    				<tr>
    					<td>First Name:</td>
    					<td><form:input path="firstName" cssClass="longText" /></td>
                        <td>&nbsp;Middle Initial:</td>
                        <td><form:input path="middleInitial" cssClass="shortText" /></td>
                        <td>&nbsp;Last Name:</td>
                        <td><form:input path="lastName" cssClass="longText" /></td>
    				</tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
                        <td><span id="firstName.errors" class="error">${firstNameErrors}</span></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
                        <td><span id="lastName" class="error">${lastNameErrors}</span></td>
                    </tr>
                </table>
                <table id="user_form_table">
    				<tr>
    					<td>Home Address:</td>
    					<td><form:input	path="registrationInformation.address.homeAddress"	cssClass="xtralongText" /></td>
    				</tr>
                    <c:set var="homeAddressErrors"><form:errors path="registrationInformation.address.homeAddress"/></c:set>
                    <tr>
                        <td>&nbsp;</td>
                        <td><span id="homeAddress.errors" class="error">${homeAddressErrors}</span></td>
                    </tr>
                </table>
                <table id="user_form_table">
    				<tr>
    					<td>City:</td>
    					<td><form:input path="registrationInformation.address.city" cssClass="longText" /></td>
                        <td>State:</td>
                        <td><form:input path="registrationInformation.address.state" cssClass="longText" /></td>
                        <td>Zip Code:</td>
                        <td><form:input path="registrationInformation.address.zipCode" cssClass="longText" /></td>
    				</tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="cityErrors"><form:errors path="registrationInformation.address.city"/></c:set>
                        <td><span id="city.errors" class="error">${cityErrors}</span></td>
                        <td>&nbsp;</td>
                        <c:set var="stateErrors"><form:errors path="registrationInformation.address.state"/></c:set>
                        <td><span id="state.errors" class="error">${stateErrors}</span></td>
                        <td>&nbsp;</td>
                        <c:set var="zipCodeErrors"><form:errors path="registrationInformation.address.zipCode"/></c:set>
                        <td><span id="zipCode.errors" class="error">${zipCodeErrors}</span></td>
                    </tr>
                </table>
            </div>
            <div id="phoneNumbers">
                <table id="user_form_table">
                    <tr>
                        <td>Home Phone Number:</td>
                        <td><form:input path="registrationInformation.homePhoneNumber" cssClass="mediumText" /></td>
                        <td>Cell Phone Number:</td>
                        <td><form:input path="registrationInformation.cellPhoneNumber" cssClass="mediumText" /></td>
                        <td>&nbsp;Date of Birth:<br/>(MM/DD/YYYY)</td>
                        <td><form:input path="registrationInformation.dateOfBirth" cssClass="longText" /></td>
                    </tr>
                    <tr>
                        <c:set var="homePhoneNumberErrors"><form:errors path="registrationInformation.homePhoneNumber"/></c:set>
                        <td colspan="2"><span id="homePhoneNumber.errors" class="error">${homePhoneNumberErrors}</span></td>
                        <c:set var="cellPhoneNumberErrors"><form:errors path="registrationInformation.cellPhoneNumber"/></c:set>
                        <td colspan="2"><span id="cellPhoneNumber.errors" class="error">${cellPhoneNumberErrors}</span></td>
                        <c:set var="dateOfBirthErrors"><form:errors path="registrationInformation.dateOfBirth"/></c:set>
                        <td colspan="2"><span id="dateOfBirth.errors" class="error">${dateOfBirthErrors}</span></td>
                    </tr>
    			</table>
            </div>
            <br/>
            <br/>
            <p>Please enter the following information to create an account, allowing you to login later to update your profile or make payments</p>
            <div id="emailAndPassword">
                <table id="user_form_table">
                    <tr>
                        <td>Email Address:&nbsp;</td>
                        <td><form:input path="emailAddress" cssClass="longText" /></td>
                        <td>Password:</td>
                        <td><form:password path="clearTextPassword" cssClass="longText" /></td>
                        <td>Confirm Password:</td>
                        <td><form:password path="clearTextConfirmPassword" cssClass="longText" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="emailAddressErrors"><form:errors path="emailAddress"/></c:set>
                        <td><span id="emailAddress" class="error">${emailAddressErrors}</span></td>
                        <td>&nbsp;</td>
                        <c:set var="clearTextPasswordErrors"><form:errors path="clearTextPassword"/></c:set>
                        <td><span id="clearTextPassword" class="error">${clearTextPasswordErrors}</span></td>
                        <td>&nbsp;</td>
                        <c:set var="clearTextConfirmPasswordErrors"><form:errors path="clearTextConfirmPassword"/></c:set>
                        <td><span id="clearTextConfirmPassword" class="error">${clearTextConfirmPasswordErrors}</span></td>
                    </tr>
                </table>
            </div>
            <div class="submitButtons">
                <span class="submitButton"><input type="submit" value="${submitButtonText}"/></span>
                <span class="submitButton"><a href="${homeUrl}"> <input type="button" value="Cancel"/></a></span>
            </div>
		</form:form>
	
	</div>
    