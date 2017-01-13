<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 06.01.17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login failed</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/login.jsp" method="get">
    There is no such user
    Please try to login again!
    <input name="login" type="submit" value="login">
</form>

</body>
</html>
