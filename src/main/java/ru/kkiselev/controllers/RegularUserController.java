package ru.kkiselev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kkiselev.model.POJO.User;
import ru.kkiselev.model.service.OrderProductServiceImpl;
import ru.kkiselev.model.service.UserService;
import ru.kkiselev.model.service.UserServiceImpl;

import javax.servlet.http.HttpSession;

/**
 * Created by kv on 19.01.17.
 */

@Controller
public class RegularUserController {

    private static final Logger LOG = LoggerFactory.getLogger(Registration.class);

    private OrderProductServiceImpl orderProductServiceImpl;
    private UserService<User> userService;

    @Autowired
    public RegularUserController(OrderProductServiceImpl orderProductServiceImplImpl, UserServiceImpl userService) {
        this.orderProductServiceImpl = orderProductServiceImpl;
        this.userService = userService;
    }

    @RequestMapping(value = "/registration")
    public String registration() {
        LOG.info("registration method called");
        return "registration";
    }

    //@RequestMapping("/login")
    public String login(@RequestParam String email, String password){

        //User user = userService.getUserByEmail(email);

//        HttpSession session = req.getSession();
//        session.setAttribute("user", userService.getUserByEmail(email));
//
//        if(userService.isAdminUser(user)){
//            req.getRequestDispatcher("admin.jsp").forward(req, resp);
//            LOG.info("Login servlet forwarded to admin.jsp");
//        } else if(userService.isRegistered(user)){
//
//            //Order order = new Order();
//            //order.setUserId(UserService.getUserByEmail(email).getId());
//
//            //OrderService orderService = new OrderService();
//            //orderService.addUser(order);
//
//            req.getRequestDispatcher("products.jsp").forward(req, resp);
//            LOG.info("Login servlet forwarded to products.jsp");
//        } else {
////            req.getRequestDispatcher("login_failed.jsp").forward(req, resp);
//            LOG.info("Login servlet forwarded to login_failed.jsp");
////        }
        return "redirect:/products";
    }

}
