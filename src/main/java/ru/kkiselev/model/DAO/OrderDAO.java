package ru.kkiselev.model.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.POJO.Order;
import ru.kkiselev.model.dbcp.DBConnection;
import ru.kkiselev.model.dbcp.DbStarter;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Created by kv on 04.01.17.
 */
public class OrderDAO implements DAO<Order> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAO.class);

    public List<Order> getAll() {
        //no implementation required
        return null;
    }

    public void addRow(Order instance) {
        LOG.info("ORDER DAO ADD_ROW");
        String ADD_ORDER = "INSERT INTO ORDERS (USER_ID, id) VALUES (?, ?) ";

        try {Connection conn = DBConnection.getConnection();

            Random random = new Random();

            PreparedStatement statement = conn.prepareStatement(ADD_ORDER);
            statement.setInt(1, instance.getUserId());
            statement.setInt(2, instance.getUserId());

            statement.execute();

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public int getOrderID(int userID){
        int order_id = 0;
        String GET_ORDER_ID = " SELECT MAX(ID) FROM ORDERS WHERE USER_ID = ? ";

        try {Connection conn = DBConnection.getConnection();

            PreparedStatement statement = conn.prepareStatement(GET_ORDER_ID);
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                order_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return order_id;
    }

    public void deleteRow(Order instance) {

    }

    public void updateRow(Order instance) {
        //noImplementation needed
    }
}
