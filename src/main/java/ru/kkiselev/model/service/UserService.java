package ru.kkiselev.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.DAO.OrderDAO;
import ru.kkiselev.model.DAO.UserDAO;
import ru.kkiselev.model.POJO.User;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public class UserService implements Service<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    static UserDAO userDAO = new UserDAO();

    public List<User> getAll() {
        return null;
    }

    public void addRow(User instance) {
        userDAO.addRow(instance);
    }

    @Override
    public void deleteRow(User instance) {

    }

    public void deleteRow(long id) {
       //
    }

    public void updateRow(User instance) {

    }

    public static User getUserByEmail(String email){
        return userDAO.getUserByEmail(email);
    }

    public boolean isRegistered(User instance){
        return userDAO.isRegistered(instance);
    }

    public boolean isAdminUser(User user) {
        return userDAO.isAdminUser(user);
    }
}
