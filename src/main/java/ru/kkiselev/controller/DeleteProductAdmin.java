package ru.kkiselev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.POJO.Product;
import ru.kkiselev.model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kv on 13.01.17.
 */
public class DeleteProductAdmin extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Registration.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int  id = Integer.parseInt(req.getParameter("product_id"));

        Product product = new Product();
        product.setId(id);

        ProductService productService = new ProductService();
        productService.deleteRow(product);

        req.getRequestDispatcher("admin.jsp").forward(req, resp);
        LOG.info("DeleteProductAdmin servlet forwarded to admin.jsp");

    }
}
