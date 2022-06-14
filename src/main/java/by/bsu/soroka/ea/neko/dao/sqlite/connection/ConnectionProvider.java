package by.bsu.soroka.ea.neko.dao.sqlite.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Connection provider to sqlite3 database.
 * @author Soroka Egor
 */
@Slf4j
public class ConnectionProvider {
    private static String DB_URL = "jdbc:sqlite:";
    private static String DB_PATH = "/home/hd/dev/neko/neko.sqlite3";
    private final static String ENABLE_FOREIGN_KEYS = "PRAGMA foreign_keys = ON;";

    private final static ConnectionProvider connectionProvider = new ConnectionProvider();

    private Connection connection;

    private ConnectionProvider() {
        this.connect();
    }


    /**
     * Connect to sqlite3 database
     * @see ConnectionProvider#DB_URL
     * @see ConnectionProvider#DB_PATH
     */

    private void connect(){
        log.trace("Connecting to database ...");
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL + DB_PATH);
            log.debug("DB opened successfully {}", connection.getMetaData());
            PreparedStatement ps = connection.prepareStatement(ENABLE_FOREIGN_KEYS);
            ps.executeUpdate();

        } catch (Exception ex){
            log.error("DB connection failed");
            ex.printStackTrace();
        }
    }

    /**
     * Return {@link ConnectionProvider} instance.
     * @return instance of {@link ConnectionProvider}
     */
    public static ConnectionProvider getInstance() {return  connectionProvider;}

    /**
     * Connection Getter
     * @return connection to sqlite3 db
     */
    public Connection getConnection() {return connection;}

    /**
     * Close connection
     * @throws SQLException thrown in case of some data-base error>
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
