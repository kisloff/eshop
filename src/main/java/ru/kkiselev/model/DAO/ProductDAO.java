package ru.kkiselev.model.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.POJO.Product;
import ru.kkiselev.model.dbcp.DBConnection;
import ru.kkiselev.model.dbcp.DbStarter;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public class ProductDAO implements DAO<Product> {

    private static final Logger LOG = LoggerFactory.getLogger(ProductDAO.class);

    public List<Product> getAll() {
        String GET_ALL_PRODUCTS = "SELECT * FROM PRODUCTS";

        List<Product> products = new ArrayList<>();

        try{Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS);

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setDescription(resultSet.getString(2));
                product.setPrice(resultSet.getInt(3));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        LOG.info("List of all products sent to service");

        return products;
    }

    public void addRow(Product instance) {
        String ADD_USER = "INSERT INTO PRODUCTS (DESCRIPTION, PRICE) VALUES  (?, ?) ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER); ){

            statement.setString(1, instance.getDescription());
            statement.setInt(2, instance.getPrice());

            statement.execute();
            LOG.info("product " + instance.getDescription() + "with price " + instance.getPrice() + " added");

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(Product instance) {
        String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE ID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT); ){

            statement.setInt(1, instance.getId());

            statement.execute();
            LOG.info("product " + instance.getId() + "deleted from Products table");

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public void updateRow(Product instance) {

    }
}
