package commandpattern;

public class MacroCommand implements Command {
	private Command[] commands;
	private String name;

	public MacroCommand(Command[] commands) {
		this.commands = commands;
	}

	public MacroCommand(String name, Command[] commands) {
		this.name = name;
		this.commands = commands;
	}

	public void execute() {
		for (Command command : commands) {
			command.execute();
		}
	}

	public void undo() {
		for (Command command : commands) {
			command.undo();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
