package by.bsu.soroka.ea.neko.service.impl;


import by.bsu.soroka.ea.neko.dao.DAOProvider;
import by.bsu.soroka.ea.neko.dao.GeneralDAOProvider;
import by.bsu.soroka.ea.neko.dao.exception.DAOException;
import by.bsu.soroka.ea.neko.dao.interfaces.AdvancedProductDAO;
import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import by.bsu.soroka.ea.neko.service.interaface.StockService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl extends BasicServiceImpl<Product> implements ProductService {
    private static DAOProvider daoProvider = GeneralDAOProvider.getDAOProvider();

    public ProductServiceImpl() {
        super(daoProvider.getProductDAO());
    }

    @Override
    public boolean removeById(int id) throws ServiceException {
        try{
            boolean res = false;
            if(basicDAO instanceof AdvancedProductDAO) {
                AdvancedProductDAO advancedProductDAO = (AdvancedProductDAO)  basicDAO;
                res = advancedProductDAO.cascadeRemoveByID(id);
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
        }
        catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int update(Product product) throws ServiceException {
        int res = -1;
        try{
            if(basicDAO instanceof AdvancedProductDAO) {
                AdvancedProductDAO advancedProductDAO = (AdvancedProductDAO)  basicDAO;
                res = advancedProductDAO.update(product);
            } else {
                throw new UnsupportedOperationException();
            }
        }
        catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
        return res;
    }
}
