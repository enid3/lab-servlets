package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class DrawProduct implements Command{
    private final static ProductService productService = ServiceProvider.getInstance().getProductService();

    public static final String ID_PARAM = "id";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter(ID_PARAM));
        log.error("id: {}", id);

        if(id != null) {
            List<Product> products = null;
            try {
                products = productService.getAll();
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            Product toDraw = null;
            for(Product p : products){
                if(p.getId() == id) {
                    toDraw = p;
                    break;
                }
            }
            if(toDraw != null) {
                resp.setContentType("image/jpg");
                resp.getOutputStream().write(toDraw.getImage());
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "no such id");
            }

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "id not specified");
        }
    }
}
