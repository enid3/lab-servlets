package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.entity.Storage;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.StorageService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.PRODUCT_FORM_PATH;
import static by.bsu.soroka.ea.neko.controller.commands.Constants.STORAGE_FORM_PATH;

@Slf4j
public class AddStorage implements Command {
    public static final String NAME_PARAMETER = "name";
    public static final String NAME_ERROR = "nameError";

    public static final String PREV_NAME_PARAM = "prev_name";

    private static final StorageService storageService
            = ServiceProvider.getInstance().getStorageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAMETER);
        req.setAttribute(PREV_NAME_PARAM, name);

        boolean isWrong = false;
        if(name == null) {
            req.setAttribute(NAME_ERROR, "bad specified");
            isWrong = true;
        }
        if(name.isEmpty()){
            req.setAttribute(NAME_ERROR, "Empty name specified");
            isWrong = true;
        }

        if(!isWrong) {
            Storage s = new Storage(name);
            try {
                int id = storageService.add(s);
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
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(STORAGE_FORM_PATH);
        dispatcher.forward(req, resp);
    }
}
