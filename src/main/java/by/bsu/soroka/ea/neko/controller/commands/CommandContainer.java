package by.bsu.soroka.ea.neko.controller.commands;


import by.bsu.soroka.ea.neko.controller.commands.cmd.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class CommandContainer {
	private Map<String, Command> commands = new HashMap<>();

	private void add(Command cmd) {
	    if (cmd != null){
			String cmdName = cmd.getClass().getSimpleName().toLowerCase(Locale.ROOT);
			commands.put(cmdName, cmd);
		}
	}

	public CommandContainer() {
		add(new DoCompare());
		add(new ToStage());
		add(new ShowCompare());
		add(new Result());
		add(new ShowResult());
		add(new ShowConfirm());
		add(new Confirm());
		add(new NextUndone());
		add(new AddStorage());
		add(new AddProduct());
		add(new AddStock());
		add(new Choose());
		add(new DrawProduct());
		add(new ProductInfo());
		add(new ApplyResults());
	}

	public Command get(String name) {
		name = name.toLowerCase(Locale.ROOT);
		return commands.getOrDefault(name, null);
	}
}
