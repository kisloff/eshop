
<%@ page import="ru.kkiselev.model.POJO.Product" %>
<%@ page import="ru.kkiselev.model.service.ProductService" %><%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 04.01.17
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>

<table border="1" cellpadding="8" cellspacing="2">
    <thead>
    <tr>
        <th>description</th>
        <th>price</th>
        <th>purchase</th>
    </tr>
    </thead>

    <%
        ProductService productService = new ProductService();
        for (Product product :productService.getAll()){
    %>

    <tr>
        <td><%=product.getDescription()%> </td>
        <td><%=product.getPrice()%></td>
        <form action="${pageContext.servletContext.contextPath}/add_to_chart" method="post">
            <td><input class="button" type="submit" value="Добавить в корзину">
                <input type="hidden" name="product_id" value="<%=product.getId()%>">
            </td>
        </form>
    </tr>
    <%}%>

</table>

<form action="${pageContext.servletContext.contextPath}/basket.jsp" method="post">
    <input class="button" type="submit" value="Перейти в корзину">
</form>
<form action="${pageContext.servletContext.contextPath}/login.jsp" method="post">
    <input class="button" type="submit" value="Разлогиниться">
</form>

</body>
</html>
