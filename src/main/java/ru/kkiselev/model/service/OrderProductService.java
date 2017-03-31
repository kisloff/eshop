package ru.kkiselev.model.service;

import org.springframework.stereotype.Component;
import ru.kkiselev.model.POJO.OrderProduct;
import ru.kkiselev.model.POJO.Product;

import java.util.List;

/**
 * Created by kv on 20.01.17.
 */

public interface OrderProductService<OrderProduct> {
    List<Product> getAllFromBasket(int userId);

    void insertProductToOrder(int product_id, int order_id);

    void deleteProductFromOrder(OrderProduct instance);
}
