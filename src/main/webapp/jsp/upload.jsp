<%--
  Created by IntelliJ IDEA.
  User: hd
  Date: 5/25/21
  Time: 1:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload images</title>
</head>
<body>
<form action="controller" method="post" enctype="multipart/form-data" >
    <input type="hidden" name="command" value="uploadImages" >
    <input type="file" id="myFile" name="filename" accept=".jpg" multiple="true">
    <input type="submit">
</form>
<h1>${requestScope.error_msg}</h1>
</body>
</html>
