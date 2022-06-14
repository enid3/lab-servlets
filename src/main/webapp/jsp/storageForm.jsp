<%@ page contentType="text/html;charset=UTF-8"  language="java" errorPage="error.jsp" %>
<%@ page import="by.bsu.soroka.ea.neko.controller.commands.cmd.AddStorage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Storage Form</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>

<form action="<c:url value="/controller" />" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="command" value="addStorage" >

    <label name="<%= AddStorage.NAME_ERROR%>" style="color: darkred">${requestScope.nameError}</label>
    <input type="text" id="<%= AddStorage.NAME_PARAMETER %>" name="<%= AddStorage.NAME_PARAMETER %>"
        value="${requestScope.prev_name}">
    <br>
    <input type="submit" value="Submit storage">
</form>

</body>
</html>