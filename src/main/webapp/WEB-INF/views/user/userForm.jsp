<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:choose>
    <c:when test="${isNew}">
        <c:set var="method" value="post"/>
        <c:set var="heading" value="Welcome! Please Register"/>
        <c:set var="submitButtonText" value="Register"/>
        <c:set var="cancelUrl" value="${contextPath}/page/home"/>
    </c:when>
    <c:otherwise>
        <c:set var="method" value="put"/>
        <c:set var="heading" value="Edit Registration Information"/>
        <c:set var="submitButtonText" value="Submit"/>
        <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
            <c:set var="cancelUrl" value="${contextPath}/page/admin/home"/>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_REGISTERED_USER')">
            <c:set var="cancelUrl" value="${contextPath}/page/user/profile"/>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <c:set var="cancelUrl" value="${contextPath}/page/home"/>
        </sec:authorize>
    </c:otherwise>
</c:choose>

	<div id="userForm" class="curved_wide">
	
		<h1>${heading}</h1>
        
		<form:form modelAttribute="user" method="${method}">
            <form:errors cssClass="errorBlock" element="div" />

            <!-- Begin Registration Information -->

            <!-- If it's a new user, explain the form -->
            <c:if test="${isNew}">
                <p>Please complete the form below so we can learn some basic information about you</p>
            </c:if>

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
            <div id="additionalRegistrationData">
                <div id="dataRow">
                    <div id="dataQuestionAnswer">
                        Have you attended a MOPS group before?
                        <form:radiobutton path="registrationInformation.attendedMopsBefore" value="true"/>Yes
                        <form:radiobutton path="registrationInformation.attendedMopsBefore" value="false"/>No
                        &nbsp;&nbsp;&nbsp;If so, where?
                    </div>
                    <div id="dataQuestionAnswer">
                        <form:input path="registrationInformation.attendedMopsBeforeLocation" cssClass="xtralongText"/>
                    </div>
                </div>
                <div id="dataRow">
                    Are you registered for the MOPS to MOM Connection through MOPS International?
                    <form:radiobutton path="registrationInformation.registeredMopsToMomConnection" value="true"/>Yes
                    <form:radiobutton path="registrationInformation.registeredMopsToMomConnection" value="false"/>No
                </div>
                <div id="dataRow">
                    <div id="dataQuestionAnswer">
                        Do you attend a church?
                        <form:radiobutton path="registrationInformation.attendChurch" value="true"/>Yes
                        <form:radiobutton path="registrationInformation.attendChurch" value="false"/>No
                        &nbsp;&nbsp;&nbsp;If so, where?
                    </div>
                    <div id="dataQuestionAnswer">
                        <form:input path="registrationInformation.attendChurchLocation" cssClass="xtralongText"/>
                    </div>
                </div>
                <div id="dataRow">
                    How did you hear about this MOPS group?<br/>
                    <form:textarea path="registrationInformation.howDidYouHearAboutMops" cssClass="xtralongText"/>
                </div>
                <div id="dataRow">
                    Husband's Name (if applicable): <form:input path="registrationInformation.husbandsName" cssClass="longText"/>
                </div>
            </div>

            <!-- End Registration Information -->

            <!-- If it's a new user, ask them to create an account -->
            <c:if test="${isNew}">
                <div id="registrationBreak">&nbsp;</div>
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
            </c:if>

            <div class="submitButtons">
                <span class="submitButton"><input type="submit" value="${submitButtonText}"/></span>
                <span class="submitButton"><a href="${cancelUrl}"> <input type="button" value="Cancel"/></a></span>
            </div>
		</form:form>
	
	</div>
    