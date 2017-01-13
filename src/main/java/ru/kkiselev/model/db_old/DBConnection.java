package ru.kkiselev.model.db_old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kv on 27.12.16.
 */
public class DBConnection {

    private static final Logger LOG = LoggerFactory.getLogger(DBConnection.class);

//    private static Connection conn;
//
//    private String url = "jdbc:h2:~/grocery";
//    //private String url = "~/grocery";
//
//    private DBConnection(){
//        try {
//            Class.forName("org.h2.Driver");
//            conn = DriverManager.getConnection(url);
//
//        } catch (ClassNotFoundException e) {
//            LOG.error(e.getMessage());
//        } catch (SQLException e) {
//            LOG.error(e.getMessage());
//        }
//    }
//
//    public static Connection getConnection(){
//        if(conn == null){
//            new DBConnection();
//        }
//        return conn;
//    }

}
