package ru.kkiselev.model.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.kkiselev.model.POJO.OrderProduct;
import ru.kkiselev.model.POJO.Product;
import ru.kkiselev.model.dbcp.DBConnection;
import ru.kkiselev.model.dbcp.DbStarter;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kv on 07.01.17.
 */

@Repository
public class OrderProductDAO  {

    private static final Logger LOG = LoggerFactory.getLogger(OrderProductDAO.class);

    public List<Product> getAll(int id) {

        List<Product> productList = new ArrayList<>();

        String SELECT_BASKET = "Select p.description, p.price, p.id " +
                "from REL_ORDERS_PRODUCTS op " +
                "inner join Products p on " +
                "op.product_id = p.id " +
                "where op.order_id = ? ";

        try{Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BASKET);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setDescription(resultSet.getString(1));
                product.setPrice(resultSet.getInt(2));
                product.setId(resultSet.getInt(3));

                productList.add(product);
            };

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public void insertProductToOrder(int product_id, int order_id){
        String INSERT_PRODUCT_TO_ORDER = "INSERT INTO REL_ORDERS_PRODUCTS (PRODUCT_ID, ORDER_ID) VALUES (?, ?)";

        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT_TO_ORDER);

            statement.setInt(1, product_id);
            statement.setInt(2, order_id);

            statement.execute();
            LOG.info(product_id + " " + order_id);

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductFromOrder(OrderProduct instance){
        String DELETE_FROM_ORDER = "Delete from REL_ORDERS_PRODUCTS where ORDER_ID = ? and PRODUCT_ID = ? ";

        try {Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_FROM_ORDER);
            statement.setInt(1, instance.getOrderId());
            statement.setInt(2, instance.getProductId());

            statement.execute();
            LOG.info("product " + instance.getProductId() + " deleted from order " + instance.getProductId());

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
