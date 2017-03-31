package ru.kkiselev.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kkiselev.model.DAO.ProductDAO;
import ru.kkiselev.model.POJO.Product;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */

@Service
public class ProductServiceImpl implements ProductService<Product> {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    public void addProduct(Product instance) {
        productDAO.addRow(instance);
    }

    public void deleteProduct(Product instance) {
        productDAO.deleteRow(instance);
    }

}
