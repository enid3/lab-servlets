package by.bsu.soroka.ea.neko.controller;

import by.bsu.soroka.ea.neko.entity.Product;
import by.bsu.soroka.ea.neko.service.ServiceProvider;
import by.bsu.soroka.ea.neko.service.exception.ServiceException;
import by.bsu.soroka.ea.neko.service.interaface.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.PRODUCTS_ATTR;

@WebServlet(
        urlPatterns = {"/images"}
)


@Slf4j
public class ImageServlet extends HttpServlet {
    private final static ProductService productService = ServiceProvider.getInstance().getProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.error("get");
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.error("post");
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("image/jpg");
        HttpSession session = req.getSession();

        Integer id = Integer.parseInt(req.getParameter("id"));
        log.error("id: {}", id);

        List<Product> products = null;
        try {
            List<Product> products = productService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if(id != null) {
            List<byte[]> imageList = (List<byte[]>) session.getAttribute("images");
            for(Product p : products){

            }
            if(imageList != null) {
                if(index >= 0 && index < imageList.size()){
                    resp.getOutputStream().write(imageList.get(index));
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "index not in ragne");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "images not loaded");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "bad index");
        }*/
    }
}
