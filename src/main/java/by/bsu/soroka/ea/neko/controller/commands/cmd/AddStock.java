package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Stock;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.StockService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.STOCK_FORM_PATH;
import static by.bsu.soroka.ea.neko.controller.commands.Constants.STORAGE_FORM_PATH;

@Slf4j
public class AddStock implements Command {
    public final static String PRODUCT_ID_PARAM = "product_id";
    public final static String PRODUCT_ID_ERROR = "product_id_error";

    public final static String STORAGE_ID_PARAM = "storage_id";
    public final static String STORAGE_ID_ERROR = "storage_id_error";

    public final static String COUNT_PARAM = "count";
    public final static String COUNT_ERROR = "count_error";
    private static final StockService stockService
            = ServiceProvider.getInstance().getStockService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sProductId = req.getParameter(PRODUCT_ID_PARAM);
        String sStorageId = req.getParameter(STORAGE_ID_PARAM);
        String sCount = req.getParameter(COUNT_PARAM);
        log.error(sProductId);
        log.error(sStorageId);
        log.error(sCount);

        boolean isWrong = false;
        if(sProductId == null) {
            req.setAttribute(PRODUCT_ID_ERROR, "bad product");
            isWrong = true;
        }

        if(sStorageId == null) {
            req.setAttribute(STORAGE_ID_ERROR, "bad storage");
            isWrong = true;
        }

        if(sCount == null) {
            req.setAttribute(COUNT_ERROR, "bad stock");
            isWrong = true;
        }

        if(!isWrong) {
            int productId = Integer.parseInt(sProductId);
            int storageId = Integer.parseInt(sStorageId);
            int count =  Integer.parseInt(sCount);

            Stock s = new Stock(productId, storageId, count);

            try {
                int id = stockService.add(s);
                log.error("ID: {}", id);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            resp.sendRedirect(req.getContextPath() + "/controller?command=choose");
        } else {
            forwardToForm(req, resp);
        }

    }

    public static final void forwardToForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(STOCK_FORM_PATH);
        dispatcher.forward(req, resp);
    }
}
