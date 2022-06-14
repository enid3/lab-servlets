package by.bsu.soroka.ea.neko.controller.commands.cmd;

import by.bsu.soroka.ea.neko.entity.Comparsion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.COMPARSION_ATTR;
import static by.bsu.soroka.ea.neko.controller.commands.Constants.STAGE_PARAMETER;


@Slf4j
public class ToStage implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Comparsion cmp = (Comparsion) session.getAttribute(COMPARSION_ATTR);
        if(cmp == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comparison not crated");
            return;
        }

        String stageString = req.getParameter(STAGE_PARAMETER);

        if(stageString != null){
            int stage = Integer.parseInt(stageString);

            if(cmp.isStageValid(stage)) {
                log.error("ok");
                cmp.setStage(stage);
                resp.sendRedirect( "controller?command=showCompare&stage=" + stage);
            } else if(stage == cmp.getStage()){
                log.error("invalid");
                int undone = cmp.getUndoneStage();
                if(undone == cmp.getSize()){
                    resp.sendRedirect(req.getContextPath() + "/controller?command=result");
                } else {
                    resp.sendRedirect( req.getContextPath() +  "/controller?command=showCompare&stage=" + stage);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "unknown stage");
            }
        }
    }
}
