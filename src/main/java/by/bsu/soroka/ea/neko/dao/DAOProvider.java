package by.bsu.soroka.ea.neko.dao;

import by.bsu.soroka.ea.neko.dao.interfaces.BasicDAO;
import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.entity.Storage;

/**
 * Provide access to data-storage
 * @author Soroka Egor
 */
public interface DAOProvider {
    BasicDAO<Product> getProductDAO();

    BasicDAO<Storage> getStorageDAO();

    BasicDAO<Stock> getStockDAO();

}
