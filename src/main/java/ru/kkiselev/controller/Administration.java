package ru.kkiselev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.ProductDAO;
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
public class Administration extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(ProductDAO.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        int  price = Integer.parseInt(req.getParameter("price"));

        Product product = new Product(description, price);

        ProductService productService = new ProductService();
        productService.addRow(product);

        req.getRequestDispatcher("admin.jsp").forward(req, resp);
        LOG.info("Admin servlet forwarded to admin.jsp");
    }
}
