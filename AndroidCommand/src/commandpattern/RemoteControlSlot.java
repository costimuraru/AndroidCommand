package commandpattern;

public class RemoteControlSlot {
	private String name;
	private Command onCommand, offCommand;

	public RemoteControlSlot(String name, Command onCommand, Command offCommand) {
		this.setName(name);
		this.setOnCommand(onCommand);
		this.setOffCommand(offCommand);
	}

	public RemoteControlSlot() {
		this.name = "No command";
		this.onCommand = this.offCommand = new NoCommand();
	}

	public boolean noCommand() {
		return onCommand instanceof NoCommand
				&& offCommand instanceof NoCommand;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setOnCommand(Command onCommand) {
		this.onCommand = onCommand;
	}

	public Command getOnCommand() {
		return onCommand;
	}

	public void setOffCommand(Command offCommand) {
		this.offCommand = offCommand;
	}

	public Command getOffCommand() {
		return offCommand;
	}
}