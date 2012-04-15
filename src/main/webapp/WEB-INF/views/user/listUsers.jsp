<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div id="main">
    	<div>
    
    		<c:if test="${empty users}">
    			<p>&nbsp;</p>
    		</c:if>
    
    		<c:if test="${not empty users}">
    			<table>
    				<tr>
    					<th>User Name</th>
    					<th>First Name</th>
    					<th>Last Name</th>
    				</tr>
    				<c:forEach var="user" items="${users}">
    					<tr>
    						<td>${user.userName}</td>
    						<td>${user.firstName}</td>
    						<td>${user.lastName}</td>
    					</tr>
    				</c:forEach>
    			</table>
    		</c:if>
    
    	</div>

        <a href="home">Return to Registration Home</a>

    </div>
