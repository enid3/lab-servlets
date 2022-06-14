package by.bsu.soroka.ea.neko.dao.interfaces;


import by.bsu.soroka.ea.neko.dao.exception.DAOException;

/**
 * @author Soroka Egor
 * Specify some special operations, that can be implemented more efficently
 * on DAO-level.
 * @see BasicDAO
 */
public interface AdvancedStockDAO {
    int getTotalProductCountByID(int productID) throws DAOException;

    int getCount(int productID, int storageID) throws DAOException;
}
