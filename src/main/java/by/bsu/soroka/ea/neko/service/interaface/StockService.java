package by.bsu.soroka.ea.neko.service.interaface;

import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;

public interface StockService extends BasicService<Stock> {
    int getTotalProductCountByID(int productID) throws ServiceException;

    int getCount(int productID, int storageID) throws ServiceException;
}
