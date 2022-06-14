<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="it" uri="imgtags" %>

<jsp:useBean id="cmp" scope="session" class="by.bsu.soroka.ea.neko.entity.Comparsion"/>

<html>
<head>
    <title>Result</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<h1>Result</h1>

<it:imgtable columns="3" group="3">
    <c:forEach var="p" items="${cmp.toCompare}">
        <h3 align="center" > <c:out value="${p.name}[${p.id}]"/> <br>
            <c:out value="rating: ${p.rating}"/> </h3>
        <it:imel>
            <c:url value="/controller">
                <c:param name="command" value="drawProduct"/>
                <c:param name="id" value="${p.id}"/>
            </c:url>
        </it:imel>
    </c:forEach>
</it:imgtable>

<form action="<c:url value="/controller"/>" method="post">
    <input type="hidden" name="command" value="applyResults">
    <input type="submit" value="apply">
</form>

</body>
</html>