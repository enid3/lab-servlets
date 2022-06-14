package by.bsu.soroka.ea.neko.dao.interfaces;


import by.bsu.soroka.ea.neko.dao.exception.DAOException;

import java.util.List;

public interface BasicDAO<T>{
    /**
     * Get all {@link T} objects from data-storage.
     * @return {@link T <Product>}
     */
    List<T> getAll() throws DAOException;

    /**
     * Add new {@link T} object to data-storage.
     * @param t {@link T} instance.
     */
    int add(T t) throws DAOException;

    /**
     * Remove Product by it's id.
     * @param id id of {T}, that will be removed.
     * @throws DAOException
     */
    boolean removeById(int id) throws DAOException;
}
