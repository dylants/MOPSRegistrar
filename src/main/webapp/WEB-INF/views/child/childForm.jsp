<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:choose>
    <c:when test="${isNew}">
        <c:set var="heading" value="Add MOPPET"/>
        <c:set var="registrationInformationURL" value="${contextPath}/page/user/profile/registrationInformation"/>
        <c:set var="submitButtonText" value="Add"/>
        <c:set var="cancelUrl" value="${contextPath}/page/user/profile/child"/>
    </c:when>
    <c:otherwise>
        <c:set var="heading" value="Edit MOPPET Registration Information"/>
        <c:set var="registrationInformationURL" value="${contextPath}/page/user/profile/registrationInformation"/>
        <c:set var="submitButtonText" value="Edit"/>
        <sec:authorize access="hasRole('ROLE_ADMIN_USER')">
            <c:set var="cancelUrl" value="${contextPath}/page/admin/home"/>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_REGISTERED_USER')">
            <c:set var="cancelUrl" value="${contextPath}/page/user/profile/child"/>
        </sec:authorize>
    </c:otherwise>
</c:choose>

    <div id="childForm" class="curved_wide">
    
        <h1>${heading}</h1>
        
        <form:form modelAttribute="child" method="POST">
            <form:errors cssClass="errorBlock" element="div" />

            <!-- If it's a new user, explain the form -->
            <c:if test="${isNew}">
                <p>Please complete the form below so we can learn some basic information about your child</p>
            </c:if>

            <div id="name">
                <table id="child_table">
                    <tr>
                        <td>First Name:</td>
                        <td><form:input path="firstName" cssClass="longText" /></td>
                        <td>&nbsp;Middle Initial:</td>
                        <td><form:input path="middleInitial" cssClass="shortText" /></td>
                        <td>&nbsp;Last Name:</td>
                        <td><form:input path="lastName" cssClass="longText" /></td>
                        <td>&nbsp;Date of Birth:<br/>(MM/DD/YYYY)</td>
                        <td><form:input path="dateOfBirth" cssClass="mediumText" /></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
                        <td><span id="firstName.errors" class="error">${firstNameErrors}</span></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
                        <td><span id="lastName.errors" class="error">${lastNameErrors}</span></td>
                        <c:set var="dateOfBirthErrors"><form:errors path="dateOfBirth"/></c:set>
                        <td colspan="2"><span id="dateOfBirth.errors" class="error">${dateOfBirthErrors}</span></td>
                    </tr>
                </table>
            </div>
            
            <!-- Mother Information -->
            
            <div id="motherInformation">
                Mother's Information<br/>
                <span class="smallText">This information can be edited through the <a href="${registrationInformationURL}">Registration Information</a> section of your profile</span>
                <div id="motherNameAddress">
                    <table id="child_table">
                        <tr>
                            <td>First Name:</td>
                            <td><input id="user.registrationInformation.firstName" value="${user.registrationInformation.firstName}" class="longText" disabled /></td>
                            <td>&nbsp;&nbsp;&nbsp;Middle Initial:</td>
                            <td><input id="user.registrationInformation.middleInitial" value="${user.registrationInformation.middleInitial}" class="shortText" disabled /></td>
                            <td>&nbsp;&nbsp;Last Name:</td>
                            <td><input id="user.registrationInformation.lastName" value="${user.registrationInformation.lastName}" class="longText" disabled /></td>
                        </tr>
                    </table>
                    <table id="child_table">
                        <tr>
                            <td>Street Address:</td>
                            <td><input id="user.registrationInformation.address.streetAddress" value="${user.registrationInformation.address.streetAddress}" class="xtralongText" disabled /></td>
                        </tr>
                    </table>
                    <table id="child_table">
                        <tr>
                            <td>City:</td>
                            <td><input id="user.registrationInformation.address.city" value="${user.registrationInformation.address.city}" class="longText" disabled /></td>
                            <td>&nbsp;State:</td>
                            <td><input id="user.registrationInformation.address.state" value="${user.registrationInformation.address.state}" class="longText" disabled /></td>
                            <td>&nbsp;Zip Code:</td>
                            <td><input id="user.registrationInformation.address.zipCode" value="${user.registrationInformation.address.zipCode}" class="longText" disabled /></td>
                        </tr>
                    </table>
                </div>
                <div id="motherPhone">
                    <table id="child_table">
                        <tr>
                            <td>Home Phone Number:</td>
                            <td><input id="user.registrationInformation.homePhoneNumber" value="${user.registrationInformation.homePhoneNumber}" class="mediumText" disabled /></td>
                            <td>&nbsp;&nbsp;&nbsp;Cell Phone Number:</td>
                            <td><input id="user.registrationInformation.cellPhoneNumber" value="${user.registrationInformation.cellPhoneNumber}" class="mediumText" disabled /></td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <!-- Father Information -->
            
            <div id="fatherInformation">
                Father's Information<br/><br/>
                <div id="fatherNameAndPhone">
                    <table id="child_table">
                        <tr>
                            <td>First Name:</td>
                            <td><form:input path="fatherFirstName" cssClass="longText" /></td>
                            <td>&nbsp;Middle Initial:</td>
                            <td><form:input path="fatherMiddleInitial" cssClass="shortText" /></td>
                            <td>&nbsp;Last Name:</td>
                            <td><form:input path="fatherLastName" cssClass="longText" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <c:set var="fatherFirstNameErrors"><form:errors path="fatherFirstName"/></c:set>
                            <td><span id="fatherFirstName.errors" class="error">${fatherFirstNameErrors}</span></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <c:set var="fatherLastNameErrors"><form:errors path="fatherLastName"/></c:set>
                            <td><span id="fatherLastName.errors" class="error">${fatherLastNameErrors}</span></td>
                        </tr>
                    </table>
                    <table id="child_table">
                        <tr>
                            <td>Emergency Contact Number:</td>
                            <td><form:input path="fatherEmergencyContactPhoneNumber" cssClass="mediumText" /></td>
                            <td>&nbsp;&nbsp;&nbsp;Does father live at home?</td>
                            <td>
                                <form:radiobutton path="fatherLiveAtHome" value="true"/>Yes
                                <form:radiobutton path="fatherLiveAtHome" value="false"/>No
                            </td>
                        </tr>
                        <tr>
                            <c:set var="fatherEmergencyContactPhoneNumberErrors"><form:errors path="fatherEmergencyContactPhoneNumber"/></c:set>
                            <td colspan="2"><span id="fatherEmergencyContactPhoneNumber.errors" class="error">${fatherEmergencyContactPhoneNumberErrors}</span></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </div>

            <!-- Family Doctor -->
            
            <div id="familyDoctor">
                Family Doctor<br/><br/>
                <div id="doctorNamePhoneAndAddress">
                    <table id="child_table">
                        <tr>
                            <td>Doctor Name:</td>
                            <td><form:input path="doctorName" cssClass="longerText" /></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Phone Number:</td>
                            <td><form:input path="doctorPhoneNumber" cssClass="mediumText" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <c:set var="doctorNameErrors"><form:errors path="doctorName"/></c:set>
                            <td><span id="doctorName.errors" class="error">${doctorNameErrors}</span></td>
                            <c:set var="doctorPhoneNumberErrors"><form:errors path="doctorPhoneNumber"/></c:set>
                            <td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span id="doctorPhoneNumber.errors" class="error">${doctorPhoneNumberErrors}</span></td>
                        </tr>
                    </table>
                    <table id="child_table">
                        <tr>
                            <td>Street Address:</td>
                            <td><form:input path="doctorAddress.streetAddress" cssClass="xtralongText" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <c:set var="doctorStreetAddressErrors"><form:errors path="doctorAddress.streetAddress"/></c:set>
                            <td><span id="doctorStreetAddress.errors" class="error">${doctorStreetAddressErrors}</span></td>
                        </tr>
                    </table>
                    <table id="child_table">
                        <tr>
                            <td>City:</td>
                            <td><form:input path="doctorAddress.city" cssClass="longText" /></td>
                            <td>&nbsp;&nbsp;State:</td>
                            <td><form:input path="doctorAddress.state" cssClass="longText" /></td>
                            <td>&nbsp;Zip Code:</td>
                            <td><form:input path="doctorAddress.zipCode" cssClass="longText" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <c:set var="doctorCityErrors"><form:errors path="doctorAddress.city"/></c:set>
                            <td><span id="doctorCity.errors" class="error">${doctorCityErrors}</span></td>
                            <td>&nbsp;</td>
                            <c:set var="doctorStateErrors"><form:errors path="doctorAddress.state"/></c:set>
                            <td><span id="doctorState.errors" class="error">${doctorStateErrors}</span></td>
                            <td>&nbsp;</td>
                            <c:set var="doctorZipCodeErrors"><form:errors path="doctorAddress.zipCode"/></c:set>
                            <td><span id="doctorZipCode.errors" class="error">${doctorZipCodeErrors}</span></td>
                        </tr>
                    </table>
                </div>
            </div>

            <!-- Emergency Contact -->
            
            <div id="emergencyContact">
                Emergency Contact<br/><br/>
                <div id="additionalEmergencyContact">
                    <table id="child_table">
                        <tr>
                            <td>Name:</td>
                            <td><form:input path="additionalEmergencyContactName" cssClass="longerText" /></td>
                            <td>&nbsp;Phone Number:</td>
                            <td><form:input path="additionalEmergencyContactPhoneNumber" cssClass="mediumText" /></td>
                            <td>&nbsp;Relationship:</td>
                            <td><form:input path="additionalEmergencyContactRelationship" cssClass="longText" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <c:set var="additionalEmergencyContactNameErrors"><form:errors path="additionalEmergencyContactName"/></c:set>
                            <td><span id="additionalEmergencyContactName.errors" class="error">${additionalEmergencyContactNameErrors}</span></td>
                            <c:set var="additionalEmergencyContactPhoneNumberErrors"><form:errors path="additionalEmergencyContactPhoneNumber"/></c:set>
                            <td colspan="2">&nbsp;<span id="additionalEmergencyContactPhoneNumber.errors" class="error">${additionalEmergencyContactPhoneNumberErrors}</span></td>
                            <td>&nbsp;</td>
                            <c:set var="additionalEmergencyContactRelationshipErrors"><form:errors path="additionalEmergencyContactRelationship"/></c:set>
                            <td><span id="additionalEmergencyContactRelationship.errors" class="error">${additionalEmergencyContactRelationshipErrors}</span></td>
                        </tr>
                    </table>
                </div>
            </div>

            <!-- Additional Information -->
            
            <div id="additionalInformation">
                Additional Information<br/><br/>
                <div id="dataRow">
                    Siblings (names and birth dates):<br/>
                    <form:textarea path="siblingsNameAndBirthDate" cssClass="xtralongText"/>
                </div>
                <div id="dataRow">
                    Favorite toys, songs, games, foods:<br/>
                    <form:textarea path="favoriteToysSongsGamesFoods" cssClass="xtralongText"/>
                </div>
                <div id="dataRow">
                    Special needs and instructions; allergies:<br/>
                    <form:textarea path="specialNeedsAndInstructionsAllergyInformation" cssClass="xtralongText"/>
                </div>
            </div>


            <div class="submitButtons">
                <span class="submitButton"><input type="submit" value="${submitButtonText}"/></span>
                <span class="submitButton"><a href="${cancelUrl}"> <input type="button" value="Cancel"/></a></span>
            </div>
        </form:form>
    
    </div>
            