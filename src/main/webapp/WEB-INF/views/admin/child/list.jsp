<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="adminRoot" value="${contextPath}/page/admin"/>

    <div id="admin_child_list" class="curved_narrow">

        <h1>List of MOPPETS</h1>

        <c:if test="${empty children}">
            <p>&nbsp;</p>
        </c:if>

        <c:if test="${not empty children}">
            <table id="child_list_table" class="sortable">
                <tr class="column_names">
                    <th><a href="#">First Name</a></th>
                    <th><a href="#">M.I.</a></th>
                    <th><a href="#">Last Name</a></th>
                    <th><a href="#">Age</a></th>
                    <th><a href="#">Date of Birth</a></th>
                </tr>
                <c:forEach var="child" items="${children}" varStatus="loopStatus">
                    <c:url value="/page/admin/child/edit/${child.entityId}" var="editUrl"/>
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
    
        <br/>
        <a href="${adminRoot}/home">Return to Admin Home</a>

    </div>

    <script src="<c:url value="/resources/js/sorttable.js" />"></script>