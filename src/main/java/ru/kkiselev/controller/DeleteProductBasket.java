package ru.kkiselev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.POJO.OrderProduct;
import ru.kkiselev.model.service.OrderProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kv on 13.01.17.
 */
public class DeleteProductBasket extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Registration.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int  product_id = Integer.parseInt(req.getParameter("product_id"));
        int  order_id = Integer.parseInt(req.getParameter("order_id"));

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(order_id);
        orderProduct.setProductId(product_id);

        OrderProductService.deleteProductFromOrder(orderProduct);

        //req.getRequestDispatcher("/basket").forward(req, resp);
        resp.sendRedirect("/basket");

        LOG.info("DeleteProductAdmin servlet forwarded to basket.jsp");
    }
}
