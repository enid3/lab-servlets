package by.bsu.soroka.ea.neko.controller.commands.cmd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.bsu.soroka.ea.neko.controller.commands.Constants.RESULT_PATH;

public class ShowResult implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(RESULT_PATH);
        dispatcher.forward(req, resp);

    }
}
