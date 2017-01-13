package ru.kkiselev.model.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kkiselev.model.POJO.User;
import ru.kkiselev.model.dbcp.DatabaseConnection;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

/**
 * Created by kv on 04.01.17.
 */
public class UserDAO implements DAO<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    public List<User> getAll() {
        //no retrieving required
        return null;
    }

    public int getIdByEmail(String email){
        int id = 0;

        String ID_BY_EMAIL = "SELECT id from USERS where email = '" + email + "'";

        try(Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();){

            ResultSet resultSet = statement.executeQuery(ID_BY_EMAIL);

            while(resultSet.next())
                id = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.info("id = "+id);
        return id;
    }

    public void addRow(User instance) {
        String ADD_USER = "INSERT INTO USERS (EMAIL, PASSWORD, ISADMIN) VALUES  (?, ?, ?) ";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER); ){

            statement.setString(1, instance.email);
            statement.setString(2, instance.password);
            statement.setBoolean(3, instance.isAdmin);

            statement.execute();
            LOG.info("user " + instance.email + "with password " + instance.password + " registered");

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }

    }

    public boolean isRegistered(User instance){
        String IS_REGISTERED = " SELECT COUNT(1) FROM USERS WHERE EMAIL = ? and PASSWORD = ? ";

        boolean isRegistered = false;

        int count = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_REGISTERED);){

            statement.setString(1, instance.email);
            statement.setString(2, instance.password);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }

        if(count > 0){
            isRegistered = true;
            LOG.info("User " + instance.email + " is already registered");
        } else {
            LOG.info("User " + instance.email + " not yet registered");
        }
        return isRegistered;
    }

    public boolean isAdminUser(User instance){
        String IS_ADMIN = "SELECT COUNT(1) FROM USERS WHERE EMAIL = ? and PASSWORD = ? and ISADMIN = 1 ";

        boolean isAdmin = false;

        int count = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement= connection.prepareStatement(IS_ADMIN);
        ){
            statement.setString(1, instance.email);
            statement.setString(2, instance.password);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);

        } catch (SQLException e) {

            LOG.error(e.getMessage());
        }

        if(count > 0){
            isAdmin = true;
            LOG.info("User " + instance.email + " is admin");
        } else {
            LOG.info("User " + instance.email + " is not admin");
        }
        return isAdmin;
    }

    public void deleteRow(User instance) {
        //no deletion required
    }

    public void updateRow(User instance) {
        //no update required
    }
}
