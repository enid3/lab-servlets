<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Navigation</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<body>
<c:set var="bcount" value="4" scope="page"/>
<div  class="btn-group" >
    <c:if test="${sessionScope.cmp != null}">
        <c:set var="bcount" scope="page" value="${bcount + 1}"/>
        <form style="width: ${100/bcount}%; " action="
        <c:url value="/controller">
            <c:param name="command" value="toStage"/>
            <c:param name="stage" value="${sessionScope.cmp.undoneStage}"/>
        </c:url>"
              method="post"> <input type="submit" value="to undone">
        </form>
    </c:if>

    <form style="width: ${95/bcount}%;" action="<c:url value="/controller"/>" method="get">
        <input type="hidden" name="command" value="choose"/>
        <input type="submit" value="choose new">
    </form>

    <form style="width: ${95/bcount}%;" action="<c:url value="/jsp/productForm.jsp"/>"  method="post">
        <input type="hidden" name="command" value="choose"/>
        <input type="submit" value="Add product form">
    </form>

    <form style="width: ${95/bcount}%;" action="<c:url value="/jsp/storageForm.jsp"/>"  method="post">
        <input type="hidden" name="command" value="choose"/>
        <input type="submit" value="Add storage form">
    </form>

    <form style="width: ${95/bcount}%;" action="<c:url value="/jsp/stockForm.jsp"/>"  method="post">
        <input type="hidden" name="command" value="choose"/>
        <input type="submit" value="Add stock form">
    </form>
</div>
</body>
</html>
