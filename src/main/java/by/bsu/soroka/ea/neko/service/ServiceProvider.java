package by.bsu.soroka.ea.neko.service;


import by.bsu.soroka.ea.neko.service.impl.ProductServiceImpl;
import by.bsu.soroka.ea.neko.service.impl.StockServiceImpl;
import by.bsu.soroka.ea.neko.service.impl.StorageServiceImpl;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import by.bsu.soroka.ea.neko.service.interaface.StockService;
import by.bsu.soroka.ea.neko.service.interaface.StorageService;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final ProductService productService = new ProductServiceImpl();
    private final StorageService storageService = new StorageServiceImpl();
    private final StockService stockService   = new StockServiceImpl();

    private ServiceProvider() {}

    /**
     * Provide instance of {@link ServiceProvider}.
     * @return instrance of {@link ServiceProvider}
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * Return specified {@link ProductService} implementation.
     * @return {@link ProductService} implementation.
     */
    public ProductService getProductService() {
        return productService;
    }

    /**
     * Return specified {@link StorageService} implementation.
     * @return {@link StorageService} implementation.
     */
    public StorageService getStorageService() {
        return storageService;
    }

    /**
     * Return specified {@link StockService} implementation.
     * @return {@link StockService} implementation.
     */
    public StockService getStockService() {
        return stockService;
    }
}

