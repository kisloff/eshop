<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 07.01.17
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/registration.jsp" method="get">
    Registration failed! Please try again!
    ${request.getAttribute("error")}
    <input name="registration_failed" type="submit" value="to registration">
</form>

</body>
</html>
