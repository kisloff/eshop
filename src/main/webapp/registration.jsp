<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 06.01.17
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/registration" method="post">
    Email:<input name="email" type="email" required/>
    Password:<input name="password" type="password" required/>
    Confirm password:<input name="confirm_password" type="password" required/>
    <input name="register" type="submit" value="submit">
</form>

</body>
</html>
