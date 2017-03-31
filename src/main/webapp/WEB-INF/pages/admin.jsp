
<%@ page import="ru.kkiselev.model.POJO.Product" %>
<%@ page import="ru.kkiselev.model.service.ProductServiceImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 06.01.17
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
</head>
<body>

<table border="1" cellpadding="8" cellspacing="2">
    <thead>
    <tr>
        <th>Description</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    </thead>


    <form action="${pageContext.servletContext.contextPath}/add_to_products" method="post">
        Description:<input name="description" type="text" required>
        Price:<input name="price" type="text" required>
        <input name="admin" type="submit" value="Add product">
    </form>

    <%--<c:forEach var="entry" items="${products}">--%>
        <%--Name:  ${entry.description} <br/>--%>
        <%--Value: ${entry.price} <br/>--%>
    <%--</c:forEach>--%>

    <%
//        ProductServiceImpl productService = new ProductServiceImpl();
//        for (Product product :productService.getAllProducts()){
    %>

<c:forEach var="entry" items="${products}">
    <tr>
        <td>${entry.description}</td>
        <td>${entry.price}</td>
        <form action="<%= request.getContextPath()%>/delete_product_admin" method="post">
            <td><input class="button" type="submit" value="Delete product">
                <input type="hidden" name="product_id" value="${entry.id}">
            </td>
        </form>
    </tr>
    <%--<%}%>--%>
</c:forEach>

</table>

</body>
</html>
