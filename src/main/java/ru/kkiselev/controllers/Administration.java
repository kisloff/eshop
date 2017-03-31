package ru.kkiselev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.ProductDAO;
import ru.kkiselev.model.POJO.Product;
import ru.kkiselev.model.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kv on 13.01.17.
 */
public class Administration extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProductDAO.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        int  price = Integer.parseInt(req.getParameter("price"));

        Product product = new Product(description, price);

        ProductServiceImpl productService = new ProductServiceImpl();
        productService.addProduct(product);

        req.getRequestDispatcher("admin.jsp").forward(req, resp);
        LOG.info("Admin servlet forwarded to admin.jsp");


        int i = 012;

        final long Byte = 0;

        Boolean b = true;


    }
}
