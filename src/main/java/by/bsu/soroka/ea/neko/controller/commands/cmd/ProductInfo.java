package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.entity.Storage;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import by.bsu.soroka.ea.neko.service.interaface.StockService;
import by.bsu.soroka.ea.neko.service.interaface.StorageService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.PRODUCT_INFO_PATH;

public class ProductInfo implements Command{
    private final static ProductService productService = ServiceProvider.getInstance().getProductService();

    public static final String ID_PARAM = "id";
    public static final String PRODUCT_ATTR = "product";
    public static final String COUNT_PER_STORAGE_ATTR = "countsPerStorage";
    public static final String TOTAL_ATTR = "total";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ID_PARAM));
        if(id != null) {
            List<Product> products = null;
            try {
                products = productService.getAll();
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            Product product = null;
            for(Product p : products){
                if(p.getId() == id) {
                    product = p;
                    break;
                }
            }
            if(product != null) {
                req.setAttribute(PRODUCT_ATTR, product);
                Map<Storage, Integer> countPerStorage = new HashMap<>();
                StockService stockService = ServiceProvider.getInstance().getStockService();
                StorageService storageService = ServiceProvider.getInstance().getStorageService();
                int total = 0;
                try {
                    for(Storage s : storageService.getAll()){
                        countPerStorage.put(s, stockService.getCount(id, s.getId()));
                    }
                    total = stockService.getTotalProductCountByID(id);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }

                req.setAttribute(COUNT_PER_STORAGE_ATTR, countPerStorage);
                req.setAttribute(TOTAL_ATTR, total);
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PRODUCT_INFO_PATH);
                dispatcher.forward(req, resp);

            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "no such id");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "id not specified");
        }
    }
}
