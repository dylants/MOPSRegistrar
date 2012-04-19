<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="adminRoot" value="${contextPath}/page/admin"/>

    <div id="admin_editSuccess">
        <div>
    
            <h1>The registration information for ${user.firstName} ${user.lastName} has been updated</h1>

        </div>

        <a href="${adminRoot}/home">Return to Admin Home</a>

    </div>
