package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.CONFIRM_PATH;
import static by.bsu.soroka.ea.neko.controller.commands.Constants.TO_COMPARE_ATTR;

@Slf4j
public class ShowConfirm implements Command {
    private static final ProductService productService = ServiceProvider.getInstance().getProductService();
    public static final String TO_COMPARE_PARAM = "toCompare";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toCompare = req.getParameter(TO_COMPARE_PARAM);
        List<Integer> ids = new ArrayList<>();
        if(toCompare != null) {
            Scanner sc = new Scanner(toCompare);
            try{
                while (sc.hasNext()) {
                    ids.add(sc.nextInt());
                    log.error("Adding: {}", ids);
                }
            }
            catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "failed to parse ints");
                return;
            }
            if(ids.size() < 2) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "use more than 2 ids");
            }
            List<Product> products = new ArrayList<>();
            try {
                for(Product p : productService.getAll()) {
                    if(ids.contains(p.getId())) {
                        products.add(p);
                    }
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            HttpSession session = req.getSession();
            session.setAttribute(TO_COMPARE_ATTR, products);
            RequestDispatcher dispatcher = req.getRequestDispatcher(CONFIRM_PATH);
            dispatcher.forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "id list empty");
        }
    }
}
