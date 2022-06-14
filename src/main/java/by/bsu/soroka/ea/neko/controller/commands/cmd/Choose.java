package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.CHOOSE_PATH;
import static by.bsu.soroka.ea.neko.controller.commands.Constants.PRODUCTS_ATTR;

public class Choose implements Command{
    ProductService productService = ServiceProvider.getInstance().getProductService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*try {
            List<Product> products = productService.getAll();
            req.setAttribute(PRODUCTS_ATTR, products);
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/

        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(CHOOSE_PATH);
            dispatcher.forward(req, resp);

    }
}
