package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.COMPARSION_ATTR;
import static by.bsu.soroka.ea.neko.controller.commands.Constants.STAGE_PARAMETER;

@Slf4j
public class DoCompare implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object cmpObj = session.getAttribute(COMPARSION_ATTR);
        if(cmpObj == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comparison not crated");
            return;
        }
        Comparsion cmp = (Comparsion) cmpObj;
        log.error("size {}", cmp.getSize());
        if(req.getParameter(STAGE_PARAMETER) != null) {
            int stage = Integer.parseInt(req.getParameter(STAGE_PARAMETER));

            log.error("stage {}", stage);
            if(!cmp.isStageValid(stage)){
                log.error("invalid stage");
                return;
            }
            cmp.setStage(stage);

            int nextStage = 0;
            if (req.getParameter("first") != null) {
                log.error("first {}", stage);
                nextStage = cmp.chooseFirst();

            } else if (req.getParameter("second") != null) {
                nextStage = cmp.chooseSecond();
                log.error("second {}", stage);
            }
            resp.sendRedirect( req.getContextPath() + "/controller?command=toStage&stage=" + nextStage);

        } else {
            return;
            //RequestDispatcher dispatcher = req.getRequestDispatcher(  "/jsp/compare.jsp");
            /*log.error("REDIRECTING");
            session.setAttribute(STAGE_PARAMETER, 0);
            dispatcher.forward(req, resp);*/
        }

    }
}
