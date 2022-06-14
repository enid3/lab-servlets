package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.COMPARSION_ATTR;

public class ApplyResults implements Command{
    private static final ProductService productService
            = ServiceProvider.getInstance().getProductService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Object cmpObj = session.getAttribute(COMPARSION_ATTR);
        if(cmpObj == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comparison not crated");
            return;
        }
        Comparsion cmp = (Comparsion) cmpObj;
        for(Product p : cmp.getToCompare()) {
            try {
                productService.update(p);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect( req.getContextPath() + "/controller?command=choose");
    }
}
