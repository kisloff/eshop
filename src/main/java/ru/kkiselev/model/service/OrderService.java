package ru.kkiselev.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.OrderDAO;
import ru.kkiselev.model.POJO.Order;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public class OrderService implements Service<Order> {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    public List<Order> getAll() {
        return null;
    }

    static OrderDAO orderDAO = new OrderDAO();

    public void addRow(Order instance) {
        LOG.info("ORDER_SERVICE ADD ROW");
        //orderDAO.addRow(instance);
        LOG.info("oreder for user id " + instance.getUserId() + "genereted" );
    }

    @Override
    public void deleteRow(Order instance) {

    }

    public void deleteRow(long id) {

    }

    public void updateRow(Order instance) {

    }

    public static int getOrderID(int userID){
        return orderDAO.getOrderID(userID);
    }
}
