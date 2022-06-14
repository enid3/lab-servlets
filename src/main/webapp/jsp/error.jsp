<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="navigation.jsp"/>

<% if(response.getStatus() == 500){ %>
<span style="color: red; ">Error: ${pageContext.errorData}</span><br>
<%}else {%>
Hi There, error code is <%=response.getStatus() %><br>
<%} %>

</body>
</html>
