package by.bsu.soroka.ea.neko.controller.commands.cmd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NextUndone implements Command{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*HttpSession session = req.getSession();
        List<Integer> result;
        Object attr = session.getAttribute("result");
        Object io =req.getAttribute("stage");
        if( attr != null) {
            result = (List<Integer>)  attr;
            int index = DoCompare.getUndone(result);
            resp.sendRedirect(req.getContextPath() + "/controller?command=tostage&stage=" + index);
        } else {
            resp.sendRedirect( req.getContextPath() + "/");
        } */
    }
}
