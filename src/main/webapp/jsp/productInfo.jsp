<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="it" uri="imgtags" %>
<html>
<c:set var="p" value="${requestScope.product}" scope="page"/>
<head>
    <title>Product ${p.name}</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<h2><c:out value="ID: ${p.id}"/></h2>
<br>
<h2><c:out value="Name: ${p.name}"/></h2>
<br>
<img align="center"
     style="width: 40%"
     src="
    <c:url value="/controller">
        <c:param name="command" value="drawProduct"/>
        <c:param name="id" value="${p.id}"/>
    </c:url>
"/>
<br>
<c:forEach var="c" items="${requestScope.countsPerStorage}">
    <c:out value="${c.key.name}:  ${c.value}" /> <br>
</c:forEach>
<c:out value="Total: ${requestScope.total}" /> <br>




</body>
</html>
