package by.bsu.soroka.ea.neko.entity;

import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductContainer  implements Serializable  {
    private final static ProductService ps = ServiceProvider.getInstance().getProductService();

    private List<Product> data = new ArrayList<>();

    public ProductContainer() throws ServiceException {
        data = ps.getAll();
    }

    public List<Product> getData() {
        return new ArrayList<>(data);
    }

}
