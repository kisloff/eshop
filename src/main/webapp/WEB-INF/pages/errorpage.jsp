<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 06.01.17
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" import="java.io.*" contentType="text/plain"%>


<html>
<head>
    <title>Error</title>
</head>
<body>

Message:
<%=exception.getMessage()%>

StackTrace:
<%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
	printWriter.println();
	printWriter.close();
	stringWriter.close();
%>

</body>
</html>
