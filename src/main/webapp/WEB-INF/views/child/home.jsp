<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userProfile" value="${contextPath}/page/user/profile"/>
<c:set var="childHome" value="${userProfile}/child"/>

    <div id="childHome" class="curved_narrow">

        <h1>MOPPETS Registration</h1>

        <c:if test="${empty children}">
            <p>No MOPPETS registered</p>
        </c:if>

        <c:if test="${not empty children}">
            <p>Below is the list of your registered MOPPETS.  Click on the name of a child to edit the registration information.
            Or click on the link below to add a child to MOPPETS.</p>
            <table id="child_list_table" class="sortable">
                <tr class="column_names">
                    <th><a href="#">First Name</a></th>
                    <th><a href="#">M.I.</a></th>
                    <th><a href="#">Last Name</a></th>
                    <th><a href="#">Age</a></th>
                    <th><a href="#">Date of Birth</a></th>
                </tr>
                <c:forEach var="child" items="${children}" varStatus="loopStatus">
                    <c:url value="/page/user/profile/child/edit/${child.entityId}" var="editUrl"/>
                    <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                        <td><a href="${editUrl}">${child.firstName}</a></td>
                        <td>${child.middleInitial}</td>
                        <td>${child.lastName}</td>
                        <td>${child.age}</td>
                        <td><spring:eval expression="child.dateOfBirth" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    
        <div id="child_home_links">
            <a href="${childHome}/add">Add a child to MOPPETS</a>
            <a href="${userProfile}">Return to Profile</a>
        </div>

    </div>

    <script src="<c:url value="/resources/js/sorttable.js" />"></script>