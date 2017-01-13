<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 04.01.17
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>

<form action="${pageContext.servletContext.contextPath}/login" method="post">
  Email:<input name="email" type="email" required>
  Password:<input name="password" type="password" required>
  <input name="login" type="submit" value="login">
</form>
<form action="${pageContext.servletContext.contextPath}/registration.jsp" method="get">
  If you are not registered, please register
  <input name="register" type="submit" value="register">
</form>

  </body>
</html>
