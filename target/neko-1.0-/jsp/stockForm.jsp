<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="by.bsu.soroka.ea.neko.controller.commands.cmd.AddStock" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stock form</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>

<jsp:useBean id="products" scope="request" class="by.bsu.soroka.ea.neko.entity.ProductContainer"/>
<jsp:useBean id="storages" scope="request" class="by.bsu.soroka.ea.neko.entity.StorageContainer"/>

<form action="<c:url value="/controller" />" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="command" value="addStock" >

    <label>Product: </label>
    <select name="<%= AddStock.PRODUCT_ID_PARAM%>">
        <c:forEach var="p" items="${products.data}">
            <option name="${p.id}" value="${p.id}"> ${p.name}</option>
        </c:forEach>
    </select>
    <label name="<%= AddStock.PRODUCT_ID_ERROR%>" style="color: darkred">${requestScope.product_id_error}</label>
    <br>

    <label>Storage: </label>
    <select name="<%= AddStock.STORAGE_ID_PARAM%>">
        <c:forEach var="s" items="${storages.data}">
            <option name="${s.id}" value="${s.id}">${s.name}</option>
        </c:forEach>
    </select>
    <label name="<%= AddStock.STORAGE_ID_ERROR%>" style="color: darkred">${requestScope.storage_id_error}</label>
    <br>

    <input name="<%= AddStock.COUNT_PARAM%>" type="number" step="1">
    <label name="<%= AddStock.COUNT_ERROR%>" style="color: darkred">${requestScope.count_error}</label>
    <br>

    <input type="submit" value="Add">
</form>

</body>
</html>
