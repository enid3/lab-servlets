package by.bsu.soroka.ea.neko.dao;


import by.bsu.soroka.ea.neko.dao.interfaces.BasicDAO;
import by.bsu.soroka.ea.neko.dao.sqlite.SqliteDAOProvider;
import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.entity.Storage;

/**
 * @author Soroka Egor
 * Warper for specifyed {@link DAOProvider}.
 */
public class GeneralDAOProvider implements DAOProvider {
    private static final DAOProvider daoProvider = SqliteDAOProvider.getInstance();

    private GeneralDAOProvider() {}

    public static DAOProvider getDAOProvider() {
        return daoProvider;
    }

    public BasicDAO<Product> getProductDAO() { return daoProvider.getProductDAO(); }

    public BasicDAO<Storage> getStorageDAO() {
        return daoProvider.getStorageDAO();
    }

    public BasicDAO<Stock> getStockDAO() {
        return daoProvider.getStockDAO();
    }
}
