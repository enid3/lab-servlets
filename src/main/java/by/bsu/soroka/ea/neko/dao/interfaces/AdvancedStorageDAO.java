package by.bsu.soroka.ea.neko.dao.interfaces;


import by.bsu.soroka.ea.neko.dao.exception.DAOException;

/**
 * @author Soroka Egor
 * Specify some special operations, that can be implemented more efficently
 * on DAO-level.
 * @see BasicDAO
 */
public interface AdvancedStorageDAO {
    boolean cascadeRemoveByID(int id) throws DAOException;
}
