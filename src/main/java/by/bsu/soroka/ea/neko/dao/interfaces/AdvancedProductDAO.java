package by.bsu.soroka.ea.neko.dao.interfaces;


import by.bsu.soroka.ea.neko.dao.exception.DAOException;
import by.bsu.soroka.ea.neko.entity.Product;

/**
 * @author Soroka Egor
 * Specify some special operations, that can be implemented more efficently
 * on DAO-level.
 * @see BasicDAO
 */
public interface AdvancedProductDAO {
    boolean cascadeRemoveByID(int id) throws DAOException;
    int update(Product product) throws DAOException;
}
