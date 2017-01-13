package ru.kkiselev.model.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import org.h2.Driver;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;
import org.h2.util.StringUtils;
/**
 * This class is inspired to {@link org.h2.server.web.DbStarter}, but it is
 * meant to be better :-)
 */
public class DbStarter implements ServletContextListener {
    private static DbStarter instance;
    private static final String CONNECTION = "connection";
    private static final String DATA_SOURCE = "datasource";
    private JdbcConnectionPool pool;
    private Connection conn; // backward compatibility
    private Server server;
    private String url;
    private String user;
    private String password;
    private String serverParams;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        instance = this;
        Driver.load();
        ServletContext ctx = ctx(sce);
        loadParams(ctx);
        startTheServerIfConfiguredToDoSo(ctx);
        setUpConnectionPool(ctx);
        setUpConnection(ctx); // backward compatibility
    }
    protected void loadParams(ServletContext ctx) {
        url = param(ctx, "db.url", "jdbc:h2:~/grocery");
        user = param(ctx, "db.user", "");
        password = param(ctx, "db.password", "");
        serverParams = param(ctx, "db.tcpServer", null);
    }
    protected void startTheServerIfConfiguredToDoSo(ServletContext ctx) {
        if (serverParams != null) {
            String[] params = StringUtils.arraySplit(serverParams, ' ', true);
            try {
                server = Server.createTcpServer(params);
                server.start();
            } catch (SQLException e) {
                ctx.log("Error during H2 server startup", e);
            }
        }
    }
    protected void setUpConnectionPool(ServletContext ctx) {
        pool = JdbcConnectionPool.create(url,user, password);
        ctx.setAttribute(DATA_SOURCE, pool);
    }
    // backward compatibility
    protected void setUpConnection(ServletContext ctx) {
        try {
            conn = pool.getConnection();
            ctx.setAttribute(CONNECTION, conn);
        } catch (SQLException e) {
            ctx.log("Error obtaining the H2 SQL connection", e);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = ctx(sce);
        closeAllOpenConnections(ctx);
        closeConnection(ctx);
        disposeConnectionPool(ctx);
        stopServer();
        instance = null;
    }
    protected void closeAllOpenConnections(ServletContext ctx) {
        try {
            Connection conn = pool.getConnection();
            try {
                Statement stat = conn.createStatement();
                try {
                    stat.execute("SHUTDOWN");
                } finally {
                    stat.close();
                }
            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            ctx.log("Error during H2 Shutdown", ex);
        }
    }
    // backward compatibility
    protected void closeConnection(ServletContext ctx) {
        try {
            conn.close();
            ctx.removeAttribute(CONNECTION);
            conn = null;
        } catch (SQLException e) {
            ctx.log("Error closing the H2 SQL Connection", e);
        }
    }
    protected void disposeConnectionPool(ServletContext ctx) {
        pool.dispose();
        ctx.removeAttribute(DATA_SOURCE);
        pool = null;
    }
    protected void stopServer() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }
    static ServletContext ctx(ServletContextEvent sce) {
        return sce.getServletContext();
    }
    /**
     * This is useful if you want to obtain a {@link JdbcConnectionPool}
     * reference type. If you don't have special reasons to do so, you should
     * use {@link #getDataSource()}
     */
    public static JdbcConnectionPool getConnectionPool() {
        return instance.pool;
    }
    public static DataSource getDataSource() {
        return instance.pool;
    }
    /**
     * This method should be compatible with previous version, but beware:
     * now you need to close the connections after using them!
     */
    public static Connection getConnection() throws SQLException {
// alternatively this method may just return 'conn' field,
// and made non-static + deprecated, to discourage the use.
        return instance.pool.getConnection();
    }
    public static Server getServer() {
        return instance.server;
    }
    protected static String param(ServletContext ctx, String key, String defval) {
        String param = ctx.getInitParameter(key);
        return param == null ? defval : param;
    }
}