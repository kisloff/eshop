package ru.kkiselev.model.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.kkiselev.model.POJO.User;
import ru.kkiselev.model.dbcp.DBConnection;
import ru.kkiselev.model.dbcp.DbStarter;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

/**
 * Created by kv on 04.01.17.
 */

@Repository
public class UserDAO implements DAO<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public List<User> getAll() {
        //no retrieving required
        return null;
    }

    public User getUserByEmail(String email){
        int id = 0;

        String ID_BY_EMAIL = "SELECT * from USERS where email = '" + email + "'";

        User user = new User();

        try{Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(ID_BY_EMAIL);

            while(resultSet.next()) {

                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAdmin(resultSet.getBoolean(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        LOG.info("id = "+id);
        return user;
    }

    public void addRow(User instance) {
        String ADD_USER = "INSERT INTO USERS (EMAIL, PASSWORD, ISADMIN) VALUES  (?, ?, ?) ";

        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER);

            statement.setString(1, instance.getEmail());
            statement.setString(2, instance.getPassword());
            statement.setBoolean(3, instance.isAdmin());

            statement.execute();
            LOG.info("user " + instance.getEmail() + "with password " + instance.getPassword() + " registered");

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public boolean isRegistered(User instance){
        String IS_REGISTERED = " SELECT COUNT(1) FROM USERS WHERE EMAIL = ? and PASSWORD = ? ";

        boolean isRegistered = false;

        int count = 0;

        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_REGISTERED);

            statement.setString(1, instance.getEmail());
            statement.setString(2, instance.getPassword());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if(count > 0){
            isRegistered = true;
            LOG.info("User " + instance.getEmail() + " is already registered");
        } else {
            LOG.info("User " + instance.getEmail() + " not yet registered");
        }
        return isRegistered;
    }

    public boolean isAdminUser(User instance){
        String IS_ADMIN = "SELECT COUNT(1) FROM USERS WHERE EMAIL = ? and PASSWORD = ? and ISADMIN = 1 ";

        boolean isAdmin = false;

        int count = 0;

        try {Connection connection = DBConnection.getConnection();
             PreparedStatement statement= connection.prepareStatement(IS_ADMIN);

            statement.setString(1, instance.getEmail());
            statement.setString(2, instance.getPassword());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException e) {

            LOG.error(e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
        }

        if(count > 0){
            isAdmin = true;
            LOG.info("User " + instance.getEmail() + " is admin");
        } else {
            LOG.info("User " + instance.getEmail() + " is not admin");
        }
        return isAdmin;
    }

    public void deleteRow(User instance) {
        //no deletion required
    }
}
