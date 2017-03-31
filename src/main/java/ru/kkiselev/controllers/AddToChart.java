package ru.kkiselev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.kkiselev.model.POJO.User;
import ru.kkiselev.model.service.OrderProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kv on 07.01.17.
 */

@Controller
public class AddToChart extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddToChart.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int product_id = Integer.parseInt(req.getParameter("product_id"));

        HttpSession session = req.getSession(false);

        User user =  (User)req.getSession().getAttribute("user");
        int user_id = user.getId();

       // int userid = (Integer) session.getAttribute("user_id");

        int orderid = user_id;

        OrderProductServiceImpl orderProductService = new OrderProductServiceImpl();
        orderProductService.insertProductToOrder(product_id, orderid);

        LOG.info("user id " + product_id + "made an order");

        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
