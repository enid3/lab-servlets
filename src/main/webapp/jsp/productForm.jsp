<%@ page contentType="text/html;charset=UTF-8"  language="java"
    import="by.bsu.soroka.ea.neko.controller.commands.cmd.AddProduct" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product Form</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<form action="<c:url value="/controller" />" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="command" value="addProduct" >

    <input type="text" id="<%= AddProduct.NAME_PARAMETER %>" name="<%= AddProduct.NAME_PARAMETER %>"
           value="${requestScope.prev_name}" >
    <label name="<%= AddProduct.NAME_ERROR%>" style="color: darkred">${requestScope.nameError}</label>

    <br>
    <input type="file" id="<%= AddProduct.IMAGE_PARAMETER %>"
           name="<%= AddProduct.IMAGE_PARAMETER %>" accept=".jpg" multiple="false">
    <label name="<%= AddProduct.IMAGE_ERROR%>" style="color: darkred">${requestScope.imageError}</label>
    <br>
    <input type="submit" value="Submit product">
</form>

</body>
</html>