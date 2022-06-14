package by.bsu.soroka.ea.neko.service.impl;

import by.bsu.soroka.ea.neko.dao.DAOProvider;
import by.bsu.soroka.ea.neko.dao.GeneralDAOProvider;
import by.bsu.soroka.ea.neko.dao.exception.DAOException;
import by.bsu.soroka.ea.neko.dao.interfaces.AdvancedStockDAO;
import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.StockService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StockServiceImpl extends BasicServiceImpl<Stock> implements StockService {
    private static DAOProvider daoProvider = GeneralDAOProvider.getDAOProvider();

    public StockServiceImpl() {
        super(daoProvider.getStockDAO());
    }

    @Override
    public int getTotalProductCountByID(int productID) throws ServiceException {
        try {
            if(basicDAO instanceof AdvancedStockDAO){
                AdvancedStockDAO advancedStockDAO = (AdvancedStockDAO) basicDAO;
                return advancedStockDAO.getTotalProductCountByID(productID);
            } else {
                log.debug("Getting total count for product with ID:{}", productID);
                List<Stock> stocks = getAll();

                int count = stocks
                        .stream()
                        .filter(stock -> {return stock.getProductID() == productID;})
                        .mapToInt(Stock::getCount)
                        .sum();

                log.debug("Total count of product: {} is {}",productID, count);
                return count;
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getCount(int productID, int storageID) throws ServiceException {
        try {
            if (basicDAO instanceof AdvancedStockDAO) {
                AdvancedStockDAO advancedStockDAO = (AdvancedStockDAO) basicDAO;
                return advancedStockDAO.getCount(productID, storageID);
            } else {
                log.debug("getting count for productID={} on storageID={}", productID, storageID);
                List<Stock> stocks = getAll();
                List<Stock> filteredStocks = stocks
                        .stream()
                        .filter(stock ->
                            stock.getProductID() == productID
                                    && stock.getStorageID() == storageID)
                        .collect(Collectors.toCollection(ArrayList::new));
                if(filteredStocks.isEmpty()){
                    Stock newStock = new Stock(productID, storageID, 0);
                    stocks.add(newStock);
                    add(newStock);
                }
                int count = filteredStocks.stream().mapToInt(Stock::getCount).sum();
                log.debug("Count of productID={}, on storageID={} is {}", productID, storageID, count);
                return count;

            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
