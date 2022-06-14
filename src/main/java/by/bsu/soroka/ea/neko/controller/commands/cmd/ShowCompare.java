package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.*;

public class ShowCompare implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object cmpObj = session.getAttribute(COMPARSION_ATTR);
        if(cmpObj == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comparison not crated");
            return;
        }
        Comparsion cmp = (Comparsion) cmpObj;

        String stageString = req.getParameter(STAGE_PARAMETER);

        if (stageString != null) {
            int stage = Integer.parseInt(stageString);

            if (cmp.isStageValid(stage)) {
                cmp.setStage(stage);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid stage:(");
            }
        }
        req.getRequestDispatcher(COMPARE_PATH).forward(req, resp);
    }
}
