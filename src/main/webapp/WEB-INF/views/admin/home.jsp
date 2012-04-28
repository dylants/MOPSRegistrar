<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="adminRoot" value="${contextPath}/page/admin"/>

    <div id="admin" class="curved_narrow">
    
        <h1>
            <strong>MOPS Registration Admin</strong>
        </h1>
    
        <p>As an Administer, you can list registered users or search for a specific user.  
        From either the List or Search pages, you can edit contents of the User.</p>

        <div id="admin_links">    
            <a href="${adminRoot}/user/list">List Registered Users</a>
            <a href="#">Search for a User</a>
        </div>
    
    </div>
