package ru.kkiselev.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kkiselev.model.DAO.UserDAO;
import ru.kkiselev.model.POJO.User;

import java.util.List;

/**
 * Created by kv on 04.01.17.
 */

@Service
public class UserServiceImpl implements UserService<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    static UserDAO userDAO = new UserDAO();

    public List<User> getAll() {
        return null;
    }

    public void addUser(User instance) {
        userDAO.addRow(instance);
    }

    public User getUserByEmail(String email){
        return userDAO.getUserByEmail(email);
    }

    public boolean isRegistered(User instance){
        return userDAO.isRegistered(instance);
    }

    public boolean isAdminUser(User user) {
        return userDAO.isAdminUser(user);
    }
}
