
<%@ page import="ru.kkiselev.model.POJO.Product" %>
<%@ page import="ru.kkiselev.model.service.ProductService" %><%--
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
        <th>description</th>
        <th>price</th>
        <th>adding</th>
    </tr>
    </thead>


    <form action="${pageContext.servletContext.contextPath}/add_to_products" method="post">
        Description:<input name="description" type="text" required>
        Price:<input name="price" type="text" required>
        <input name="admin" type="submit" value="Добавить товар">
    </form>

    <%
        ProductService productService = new ProductService();
        for (Product product :productService.getAll()){
    %>

    <tr>
        <td><%=product.getDescription()%> </td>
        <td><%=product.getPrice()%></td>
        <form action="<%= request.getContextPath()%>/delete_product_admin" method="post">
            <td><input class="button" type="submit" value="Удалить товар">
                <input type="hidden" name="product_id" value="<%=product.getId()%>">
            </td>
        </form>
    </tr>
    <%}%>

</table>

</body>
</html>
