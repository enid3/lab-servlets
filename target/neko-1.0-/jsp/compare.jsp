<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="it" uri="imgtags" %>

<jsp:useBean id="cmp" scope="session" class="by.bsu.soroka.ea.neko.entity.Comparsion"/>
<c:set var="first" scope="page" value="${cmp.currentFirst}"/>
<c:set var="second" scope="page" value="${cmp.currentSecond}"/>
<html>
<head>
    <title>Compare ${cmp.stage}</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<body>

<div class="empty-btn-group" style="width: 100%">
    <c:forEach var="i" begin="0" end="${cmp.size - 1}">
        <c:choose>
            <c:when test="${cmp.isStageDone(i)}">
                <c:set var="style" scope="page" value="width:${95/cmp.size}; background-color: darkred"/>
            </c:when>
            <c:otherwise>
                <c:set var="style" scope="page" value="width:${95/cmp.size}; background-color: blue"/>
            </c:otherwise>
        </c:choose>

        <form style="${style}" method="get" action="
            <c:url value="/controller">
            </c:url>">
            <input type="hidden" name="command" value="showCompare"/>
            <input type="hidden" name="stage" value="${i}"/>
            <input type="submit" value="${i}"/>
        </form>
    </c:forEach>
</div>


<h2>Comparing stage ${cmp.stage}</h2>
<c:choose>
    <c:when test="${cmp.getResult(cmp.stage) == 0}">
        <h4><c:out value="${first} chosen."/></h4>
    </c:when>
    <c:when test="${cmp.getResult(cmp.stage) == 1}">
        <h4> <c:out value="${second.name} chosen."/> </h4>
    </c:when>
</c:choose>

<it:imgtable columns="2" group="6">
        <it:imel>
            <c:url value="/controller">
                <c:param name="command" value="drawProduct"/>
                <c:param name="id" value="${first.id}"/>
            </c:url>
        </it:imel>
        <form action="controller"  method="post">
            <input type="hidden" name="command" value="doCompare">
            <input type="hidden" name="stage" value="${cmp.stage}">
            <input type="submit" name="first" id="first" value="<c:out value="name: ${first.name}"/>">
        </form>

        <it:imel>
            <c:url value="/controller">
                <c:param name="command" value="drawProduct"/>
                <c:param name="id" value="${second.id}"/>
            </c:url>
        </it:imel>
        <form action="controller"  method="post">
            <input type="hidden" name="command" value="doCompare">
            <input type="hidden" name="stage" value="${cmp.stage}">
            <input type="submit" name="second" id="second" value="<c:out value="name: ${second.name}"/>">
        </form>
</it:imgtable>

<c:if test="${cmp.isStageValid(cmp.stage - 1)}">
    <form action="<c:url value="/controller">
            <c:param name="command" value="toStage"/>
            <c:param name="stage" value="${cmp.stage - 1}"/>
        </c:url>" method="post">
        <input type="submit" value="prev">
    </form>
</c:if>
<c:if test="${cmp.isStageValid(cmp.stage + 1)}">
    <form action="<c:url value="/controller">
            <c:param name="command" value="toStage"/>
            <c:param name="stage" value="${cmp.stage + 1}"/>
        </c:url>" method="post">
        <input type="submit" value="next">
    </form>
</c:if>
<%--
<c:if test="${cmp.undoneStage != cmp.stage}">
<a href="
        <c:url value="/controller">
            <c:param name="command" value="showCompare"/>
            <c:param name="stage" value="${cmp.undoneStage}"/>
        </c:url>
    ">to undone stage</a>
</c:if>
--%>
</body>
</html>
