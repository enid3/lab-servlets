package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.COMPARSION_ATTR;

@Slf4j
public class Result implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Comparsion cmp = (Comparsion) session.getAttribute(COMPARSION_ATTR);
        if(cmp == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comparison not crated");
            return;
        }
        if(cmp.getUndoneStage() == cmp.getSize()) {
            cmp.calcRatings();
        } else {
            resp.sendRedirect(req.getContextPath() + "/controller?command=toStage&stage=" + cmp.getUndoneStage());
        }

        resp.sendRedirect( req.getContextPath() + "/controller?command=showResult");
    }
}
