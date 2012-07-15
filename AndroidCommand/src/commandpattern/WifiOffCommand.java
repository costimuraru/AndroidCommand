package commandpattern;

public class WifiOffCommand implements Command {
	private Wifi wifi;

	public WifiOffCommand(Wifi wifi) {
		this.wifi = wifi;
	}

	public void execute() {
		wifi.off();
	}

	public void undo() {
		wifi.on();
	}

}
