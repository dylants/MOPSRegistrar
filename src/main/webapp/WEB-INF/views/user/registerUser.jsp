<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <div id="register">
        <div>
    
    	<h1>Welcome! Please register</h1>

        <div id="register_form">    
        	<form:form modelAttribute="user" action="processRegisterUser" method="put">
        		<table>
        			<tr>
        				<td>First Name:</td>
        				<td><form:input path="firstName" cssClass="longText" /></td>
        			</tr>
        			<tr>
        				<td>Last Name:</td>
        				<td><form:input path="lastName" cssClass="longText" /></td>
        			</tr>
                    <tr>
                        <td>Email Address:</td>
                        <td><form:input path="emailAddress" cssClass="longText" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><form:password path="password" cssClass="longText" /></td>
                    </tr>
        			<tr>
        				<td colspan="2"><input type="submit" value="Register" /></td>
        			</tr>
        		</table>
        	</form:form>
        </div>
    
        </div>

    	<a href="home">Cancel Registration</a>

    </div>
