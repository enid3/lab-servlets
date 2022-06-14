package by.bsu.soroka.ea.neko.service.interaface;



import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;


public interface ProductService extends BasicService<Product> {
    int update(Product product) throws ServiceException;
}
