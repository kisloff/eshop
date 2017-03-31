package ru.kkiselev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kkiselev.model.POJO.OrderProduct;
import ru.kkiselev.model.POJO.Product;
import ru.kkiselev.model.service.OrderProductService;
import ru.kkiselev.model.service.OrderProductServiceImpl;
import ru.kkiselev.model.service.ProductServiceImpl;

import javax.servlet.http.HttpSession;

/**
 * Created by kv on 19.01.17.
 */

@Controller
public class AdminUserController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminUserController.class);

    private OrderProductService orderProductService;

    @Autowired
    public AdminUserController(OrderProductService orderProductServiceImpl) {
        this.orderProductService = orderProductServiceImpl;
    }

    @RequestMapping("/admin")
    public ModelAndView showAdminPage() {
        LOG.info("admin page");

        ModelAndView modelAndView = new ModelAndView("admin");

        modelAndView.addObject("products", new ProductServiceImpl().getAllProducts());

        return modelAndView;
    }

    @RequestMapping("/delete_product_admin")
    public String deleteFromBasket(@RequestParam int product_id){

        Product product = new Product();
        product.setId(product_id);

        ProductServiceImpl productService = new ProductServiceImpl();
        productService.deleteProduct(product);

        LOG.info("AdminUserController deleteFromBasket");
        return "redirect:/admin";
    }

    @RequestMapping("/add_to_products")
    public String addNewProduct(@RequestParam String description, int  price){

        Product product = new Product(description, price);

        ProductServiceImpl productService = new ProductServiceImpl();
        productService.addProduct(product);

        return "redirect:/admin";
    }
}



