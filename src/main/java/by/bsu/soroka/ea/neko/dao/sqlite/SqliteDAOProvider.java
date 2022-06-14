package by.bsu.soroka.ea.neko.dao.sqlite;


import by.bsu.soroka.ea.neko.dao.interfaces.BasicDAO;
import by.bsu.soroka.ea.neko.dao.sqlite.impl.SQLProductDAO;
import by.bsu.soroka.ea.neko.dao.sqlite.impl.SQLStorageDAO;
import by.bsu.soroka.ea.neko.dao.DAOProvider;
import by.bsu.soroka.ea.neko.dao.sqlite.impl.SQLStockDAO;
import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.entity.Storage;

/**
 * @author Soroka Egor
 * Provide access to sql-based data-storage
 */
public class SqliteDAOProvider implements DAOProvider {
    private static final SqliteDAOProvider instance = new SqliteDAOProvider();

    private final BasicDAO<Product> productDAO  = new SQLProductDAO();
    private final BasicDAO<Storage> storageDAO  = new SQLStorageDAO();
    private final BasicDAO<Stock> stockDAO      = new SQLStockDAO();

    private SqliteDAOProvider() {}

    public static SqliteDAOProvider getInstance() {
        return instance;
    }

    /**
     * Get sqlite-based implementation of {@link BasicDAO} for {@link Product}.
     * @return {@link BasicDAO} for {@link Product}
     */
    public BasicDAO<Product> getProductDAO() {
        return productDAO;
    }

    /**
     * Get sqlite-based implementation of {@link BasicDAO} for {@link Storage}.
     * @return {@link BasicDAO} for {@link Storage}
     */
    public BasicDAO<Storage> getStorageDAO() {
        return storageDAO;
    }

    /**
     * Get sqlite-based implementation of {@link BasicDAO} for {@link Stock}.
     * @return {@link BasicDAO} for {@link Stock}
     */
    public BasicDAO<Stock> getStockDAO() {
        return stockDAO;
    }
}
