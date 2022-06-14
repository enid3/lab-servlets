package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import by.bsu.soroka.ea.neko.entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.*;

public class Confirm implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object toCompareObj = session.getAttribute(TO_COMPARE_ATTR);
        if(toCompareObj != null) {
            List<Product> toCompare = (List<Product>) toCompareObj;
            Comparsion cmp  = new Comparsion();
            cmp.init(toCompare);
            session.setAttribute(COMPARSION_ATTR, cmp);
            resp.sendRedirect( req.getContextPath() + "/controller?command=toStage&stage=" + cmp.getStage());
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nothing to compare");
        }

    }
}
