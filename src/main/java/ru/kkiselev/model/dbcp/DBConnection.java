package ru.kkiselev.model.dbcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by kv on 04.01.17.
 */
public class DBConnection {
    private static DataSource source;

    private static final Logger LOG = LoggerFactory.getLogger(ru.kkiselev.model.dbcp.DBConnection.class);

    private static void init() throws NamingException {
        try {
            InitialContext context = new InitialContext();
            source = (DataSource) context.lookup("java:comp/env/jdbc/grocery");
            LOG.info("DB connection pool started");

        } catch (NamingException e) {
            LOG.error(e.getExplanation());
            throw e;
        }
    }

    public static Connection getConnection() throws SQLException, NamingException {
        if (source == null) {
            init();
        }
        return source.getConnection();
    }
}
