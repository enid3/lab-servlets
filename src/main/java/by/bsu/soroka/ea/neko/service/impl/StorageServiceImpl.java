package by.bsu.soroka.ea.neko.service.impl;

import by.bsu.soroka.ea.neko.dao.DAOProvider;
import by.bsu.soroka.ea.neko.dao.GeneralDAOProvider;
import by.bsu.soroka.ea.neko.dao.exception.DAOException;
import by.bsu.soroka.ea.neko.dao.interfaces.AdvancedProductDAO;
import by.bsu.soroka.ea.neko.dao.interfaces.AdvancedStorageDAO;
import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.entity.Storage;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.StockService;
import by.bsu.soroka.ea.neko.service.interaface.StorageService;

import java.util.List;
import java.util.stream.Collectors;

public class StorageServiceImpl extends BasicServiceImpl<Storage> implements StorageService {
    private static DAOProvider daoProvider = GeneralDAOProvider.getDAOProvider();

    public StorageServiceImpl() {
        super(daoProvider.getStorageDAO());
    }

    @Override
    public boolean removeById(int id) throws ServiceException {
        try {
            boolean res = false;
            if(basicDAO instanceof AdvancedProductDAO) {
                AdvancedStorageDAO advancedStorageDAO = (AdvancedStorageDAO)  basicDAO;
                res = advancedStorageDAO.cascadeRemoveByID(id);
            } else {
                StockService stockService = ServiceProvider.getInstance().getStockService();

                List<Stock> stocksForProduct = stockService.getAll()
                        .stream()
                        .filter(s -> s.getProductID() == id)
                        .collect(Collectors.toList());

                for (Stock stock : stocksForProduct) {
                    stockService.removeById(stock.getId());
                }

                res = basicDAO.removeById(id);
            }

            return res;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
