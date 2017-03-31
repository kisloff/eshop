<%@ page import="ru.kkiselev.model.service.OrderProductServiceImpl" %>
<%@ page import="ru.kkiselev.model.POJO.Product" %>
<%@ page import="ru.kkiselev.model.service.UserServiceImpl" %>
<%@ page import="java.lang.*"%>
<%@ page import="ru.kkiselev.model.POJO.User" %>
<%--
  Created by IntelliJ IDEA.
  User: kv
  Date: 11.01.17
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
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
       User user =  (User)request.getSession().getAttribute("user");
        int user_id = user.getId();
        OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();

        for (Product product : orderProductService.getAllFromBasket(user_id)){
    %>

    <tr>
        <td><%=product.getDescription()%> </td>
        <td><%=product.getPrice()%></td>
        <form action="${pageContext.request.contextPath}/delete_product_basket" method="post">
            <td><input class="button" type="submit" value="Убрать из корзины">
                <input type="hidden" name="product_id" value="<%=product.getId()%>">
                <input type="hidden" name="order_id" value="<%=user_id%>">
            </td>
        </form>
    </tr>
    <%}%>

</table>

</body>
</html>
