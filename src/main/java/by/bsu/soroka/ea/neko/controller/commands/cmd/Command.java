package by.bsu.soroka.ea.neko.controller.commands.cmd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command {
	void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
