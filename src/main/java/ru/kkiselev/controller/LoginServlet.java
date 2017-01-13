package ru.kkiselev.controller;

import org.h2.engine.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.UserDAO;
import ru.kkiselev.model.POJO.Order;
import ru.kkiselev.model.POJO.User;
import ru.kkiselev.model.service.OrderService;
import ru.kkiselev.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kv on 04.01.17.
 */
public class LoginServlet extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User();
        user.email = email;
        user.password = password;

        UserService userService = new UserService();

        HttpSession session = req.getSession();

        session.setAttribute("user_id", UserService.getIdByEmail(email));
        session.setAttribute("email", email);

        if(userService.isAdminUser(user)){
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
            LOG.info("Login servlet forwarded to admin.jsp");
        } else if(userService.isRegistered(user)){

            Order order = new Order();
            order.setUserId(UserService.getIdByEmail(email));

            OrderService orderService = new OrderService();
            orderService.addRow(order);

            req.getRequestDispatcher("products.jsp").forward(req, resp);
            LOG.info("Login servlet forwarded to products.jsp");
        } else {
            req.getRequestDispatcher("login_failed.jsp").forward(req, resp);
            LOG.info("Login servlet forwarded to login_failed.jsp");
        }
    }
}
