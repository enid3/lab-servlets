package by.bsu.soroka.ea.neko.controller;

import by.bsu.soroka.ea.neko.controller.commands.CommandContainer;
import by.bsu.soroka.ea.neko.controller.commands.cmd.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        urlPatterns = {"/controller"}
)
@MultipartConfig
public class ControllerServlet extends HttpServlet {

    private CommandContainer commandContainer = new CommandContainer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmdName = req.getParameter("command");
        Command cmd;
        if(cmdName != null) {
            cmd = commandContainer.get(cmdName);
            if (cmd != null) {
                cmd.execute(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "no such command");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "no command specified");
        }
    }
}