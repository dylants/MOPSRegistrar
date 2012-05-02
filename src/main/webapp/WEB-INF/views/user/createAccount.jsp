<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div id="createAccount" class="curved_narrow">
	
		<h1>Create Account</h1>
        
		<form:form modelAttribute="createAccountModel" method="POST">
            <form:errors cssClass="errorBlock" element="div" />

            <p>Please enter in your email address and a password to create an account.  This will 
            allow you to register for MOPS, MOPPETS, as well as re-register for either in the future.</p>

            <div id="emailAndPassword">
                <table id="create_account_table">
                    <tr>
                        <td>Email Address:&nbsp;</td>
                        <td><form:input path="emailAddress" cssClass="longText" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="emailAddressErrors"><form:errors path="emailAddress"/></c:set>
                        <td><span id="emailAddress" class="error">${emailAddressErrors}</span></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><form:password path="password" cssClass="longText" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
                        <td><span id="password" class="error">${passwordErrors}</span></td>
                    </tr>
                    <tr>
                        <td>Confirm Password:</td>
                        <td><form:password path="confirmPassword" cssClass="longText" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="confirmPasswordErrors"><form:errors path="confirmPassword"/></c:set>
                        <td><span id="confirmPassword" class="error">${confirmPasswordErrors}</span></td>
                    </tr>
                </table>
            </div>

            <div class="submitButtons">
                <span class="submitButton"><input type="submit" value="Create Account"/></span>
                <span class="submitButton"><a href="${contextPath}/page/home"> <input type="button" value="Cancel"/></a></span>
            </div>
		</form:form>
	
	</div>
    