<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
    String redirectURL = "page/home";
    response.sendRedirect(redirectURL);
%>
