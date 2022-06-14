<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="it" uri="imgtags" %>

<jsp:useBean id="products" scope="request" class="by.bsu.soroka.ea.neko.entity.ProductContainer"/>

<html>
<head>
    <title>Choose</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<body>

<jsp:include page="navigation.jsp"/>


<form style="margin-left: auto; margin-right: auto" action="<c:url value="/controller"/>" method="get">
    <input type="hidden" name="command" value="showConfirm">
    <input type="text" id="toCompare" name="toCompare">
    <input type="submit" value="compare!">
</form>

<it:imgtable columns="3" group="5">
    <c:forEach var="p" items="${products.data}">
        <div class="test"> <h3><c:out value="${p.name}[${p.id}]"/></h3> <h3><c:out value="rating: ${p.rating}"/></h3> </div>
        <it:imel>
            <c:url value="/controller">
                <c:param name="command" value="drawProduct"/>
                <c:param name="id" value="${p.id}"/>
            </c:url>
        </it:imel>
        <div class="info">
            <a class="info" href=" <c:url value="/controller"> <c:param name="command" value="productInfo"/> <c:param name="id" value="${p.id}"/> </c:url>">info</a>
        </div>
    </c:forEach>
</it:imgtable>
</body>
</html>
