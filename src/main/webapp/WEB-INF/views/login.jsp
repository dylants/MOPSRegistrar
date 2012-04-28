<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="login" class="curved_narrow">

    <h1>Login to MOPS Registrar</h1>
    
    <c:if test="${not empty errorMessage}">
        <div class="errorBlock">${errorMessage}</div>
    </c:if>
    <form action="${contextPath}/j_spring_security_check" method="POST">
        <div class="usernameAndPassword">
            <table>
                <tr>
                    <td>Email Address:</td>
                    <td><input name="j_username" type="text" class="longText"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input name="j_password" type="password" class="longText"/></td>
                </tr>
            </table>
        </div>
        <div class="submitButtons">
            <span class="submitButton"><input name="submit" type="submit" value="Login"/></span>
        </div>
    </form>

</div>