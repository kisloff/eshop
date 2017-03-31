package ru.kkiselev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.UserDAO;
import ru.kkiselev.model.POJO.User;
import ru.kkiselev.model.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kv on 04.01.17.
 */


public class Registration  extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(Registration.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        User user = new User();
        user.email = email;
        user.password = password;

        UserDAO ud = new UserDAO();

        UserServiceImpl userService = new UserServiceImpl();

        if(!password.equals(confirmPassword)){
            req.getRequestDispatcher("registration_failed.jsp").forward(req, resp);
            LOG.info("Registration servlet forwarded to registration_failed.jsp");
        } else if(userService.isRegistered(user)){
            req.setAttribute("error", "User already regeristered");
            req.getRequestDispatcher("registration_failed.jsp").forward(req, resp);
            LOG.info("Registration servlet forwarded to registration_failed.jsp");
        } else {
            user.isAdmin = false;
            ud.addRow(user);
            req.getRequestDispatcher("products.jsp").forward(req, resp);
            LOG.info("Registration servlet forwarded to products.jsp");
        }

        userService.addUser(user);
        LOG.info("User " + user.email + " sucesfully registered");
    }
}
