<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="it" uri="imgtags" %>
<html>
<head>
    <title>Confirm Compare</title>
</head>
<body>

<it:imgtable columns="3" group="4">
    <c:forEach var="p" items="${sessionScope.toCompare}">
        <h3 align="center" > <c:out value="${p.name}[${p.id}]"/> </h3>
        <it:imel>
            <c:url value="/controller">
                <c:param name="command" value="drawProduct"/>
                <c:param name="id" value="${p.id}"/>
            </c:url>
        </it:imel>
    </c:forEach>
</it:imgtable>


<form action="<c:url value="/controller" />" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="confirm">
    <input type="submit">
</form>

</body>
</html>
