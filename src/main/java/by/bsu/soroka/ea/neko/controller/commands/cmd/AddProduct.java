package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.PRODUCT_FORM_PATH;

@Slf4j
public class AddProduct implements Command {
    public static final String NAME_PARAMETER = "name";
    public static final String IMAGE_PARAMETER = "image";

    public static final String PREV_NAME_PARAM = "prev_name";

    public static final String NAME_ERROR = "nameError";
    public static final String IMAGE_ERROR = "imageError";

    private static final ProductService productService
            = ServiceProvider.getInstance().getProductService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME_PARAMETER);
        req.setAttribute(PREV_NAME_PARAM, name);
        byte[] image = null;

        List<Part> parts = req.getParts()
                .stream()
                .filter(part -> IMAGE_PARAMETER.equals(part.getName()) && part.getSize() > 0)
                .collect(Collectors.toList());
        if(parts.size() >= 1) {
            Part imgPart = parts.get(0);
            image = imgPart.getInputStream().readAllBytes();
            log.error("image length: {}", image.length);
        }
        boolean isWrong = false;
        if(name == null) {
            req.setAttribute(NAME_ERROR, "bad specified");
            isWrong = true;
        }
        if(name.isEmpty()){
            req.setAttribute(NAME_ERROR, "Empty name specified");
            isWrong = true;
        }
        if(image == null) {
            req.setAttribute(IMAGE_ERROR, "bad image specified");
            isWrong = true;
        }

        if(!isWrong) {
            Product p = new Product(name, image);
            try {
                int id = productService.add(p);
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
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PRODUCT_FORM_PATH);
        dispatcher.forward(req, resp);
    }

}
