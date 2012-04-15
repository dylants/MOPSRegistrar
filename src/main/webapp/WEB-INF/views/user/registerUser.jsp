<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <div id="main">
        <div>
    
    	<h1>Welcome! Please register</h1>
    
    	<form:form modelAttribute="user" action="processAddUser" method="put">
    		<table>
    			<tr>
    				<td>User Name:</td>
    				<td><form:input path="userName" /></td>
    			</tr>
    			<tr>
    				<td>First Name:</td>
    				<td><form:input path="firstName" /></td>
    			</tr>
    			<tr>
    				<td>Last Name:</td>
    				<td><form:input path="lastName" /></td>
    			</tr>
    			<tr>
    				<td colspan="2"><input type="submit" value="Register" /></td>
    			</tr>
    		</table>
    	</form:form>
    
        </div>

    	<a href="home">Cancel Registration</a>

    </div>
