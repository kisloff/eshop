package ru.kkiselev.model.service;

import java.util.List;

/**
 * Created by kv on 20.01.17.
 */
public interface ProductService<Product> {

    List<Product> getAllProducts();

    void addProduct(Product instance);

    void deleteProduct(Product instance);

}
