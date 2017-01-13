package ru.kkiselev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.OrderDAO;
import ru.kkiselev.model.POJO.Order;
import ru.kkiselev.model.service.OrderProductService;
import ru.kkiselev.model.service.OrderService;
import ru.kkiselev.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kv on 07.01.17.
 */
public class AddToChart extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddToChart.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int product_id = Integer.parseInt(req.getParameter("product_id"));

        HttpSession session = req.getSession(false);

        int userid = (Integer) session.getAttribute("user_id");

        int orderid = OrderService.getOrderID(userid);

        OrderProductService.insertProductToOrder(product_id, orderid);

        LOG.info("user id " + product_id + "made an order");

        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}