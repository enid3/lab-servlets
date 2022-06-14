package by.bsu.soroka.ea.neko.service.interaface;


import by.bsu.soroka.ea.neko.service.exception.ServiceException;

import java.util.List;

public interface BasicService<T> {
    List<T> getAll() throws ServiceException;

    int add(T t) throws ServiceException;

    boolean removeById(int id) throws ServiceException;
}
