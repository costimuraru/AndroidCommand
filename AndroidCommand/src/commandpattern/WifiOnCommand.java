package commandpattern;

public class WifiOnCommand implements Command {
	private Wifi wifi;

	public WifiOnCommand(Wifi wifi) {
		this.wifi = wifi;
	}

	public void execute() {
		wifi.on();
	}

	public void undo() {
		wifi.off();
	}

}
