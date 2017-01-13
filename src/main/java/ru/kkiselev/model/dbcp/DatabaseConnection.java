package ru.kkiselev.model.dbcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kv on 11.01.17.
 */
public class DatabaseConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
    public static final String DB_URL = "jdbc:h2:~/grocery";
    public static final String LOGIN = "";
    public static final String PASSWORD = "";
    public static final String H2_DRIVHER = "org.h2.Driver";

    private static Connection conn;

    public static Connection getConnection(){
        try{
            Class.forName(H2_DRIVHER);
            conn = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
            LOGGER.info("Connected to DataBase");
        } catch (ClassNotFoundException e){
            DatabaseConnection.LOGGER.info("Can't get driver: " + e.getMessage());
            conn = null;
        } catch (SQLException e) {
            DatabaseConnection.LOGGER.info(e.getMessage());
            conn = null;
        }
        return conn;
    }
}
