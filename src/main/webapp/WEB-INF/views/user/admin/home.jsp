<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userRoot" value="${contextPath}/page/user"/>
<c:set var="adminRoot" value="${userRoot}/admin"/>

    <div id="admin">
    
        <h1>
            <strong>MOPS Registration Admin</strong>
        </h1>
    
        <p>As an Administer, you can list registered users or search for a specific user.  
        From either the List or Search pages, you can edit contents of the User.</p>

        <div id="admin_links">    
            <a href="${adminRoot}/list">List Registered Users</a>
            <a href="${adminRoot}/search">Search for a User</a>
        </div>
    
    </div>
