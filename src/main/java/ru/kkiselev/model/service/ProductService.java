package ru.kkiselev.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.OrderDAO;
import ru.kkiselev.model.DAO.ProductDAO;
import ru.kkiselev.model.POJO.Product;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public class ProductService implements Service<Product> {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    ProductDAO productDAO = new ProductDAO();

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public void addRow(Product instance) {
        productDAO.addRow(instance);
    }

    public void deleteRow(Product instance) {
        productDAO.deleteRow(instance);
    }

    public void updateRow(Product instance) {

    }
}
