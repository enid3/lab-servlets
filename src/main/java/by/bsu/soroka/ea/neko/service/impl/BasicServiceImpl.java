package by.bsu.soroka.ea.neko.service.impl;

import by.bsu.soroka.ea.neko.dao.exception.DAOException;
import by.bsu.soroka.ea.neko.dao.interfaces.BasicDAO;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.BasicService;

import java.util.ArrayList;
import java.util.List;

public class BasicServiceImpl<T> implements BasicService<T> {
    protected BasicDAO<T> basicDAO;

    BasicServiceImpl(BasicDAO<T> basicDAO) {
        this.basicDAO = basicDAO;
    }

    @Override
    public List<T> getAll() throws ServiceException{
        List<T> data = new ArrayList<>();
        try {
            data.addAll(basicDAO.getAll());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

        return data;
    }

    @Override
    public int add(T t) throws ServiceException {
        try {
            return basicDAO.add(t);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean removeById(int id) throws ServiceException {
        try {
            return basicDAO.removeById(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
