package ru.kkiselev.model.service;

import ru.kkiselev.model.DAO.OrderProductDAO;
import ru.kkiselev.model.POJO.OrderProduct;
import ru.kkiselev.model.POJO.Product;

import java.util.List;

/**
 * Created by kv on 11.01.17.
 */
public class OrderProductService {

    static OrderProductDAO orderProductDAO = new OrderProductDAO();

    public static List<Product> getAllFromBasket(int userId){
        return orderProductDAO.getAll(userId);
    }

    public static void insertProductToOrder(int product_id, int order_id){
        orderProductDAO.insertProductToOrder(product_id, order_id);
    }

    public static void deleteProductFromOrder(OrderProduct instance){
        orderProductDAO.deleteProductFromOrder(instance);
    }
}
