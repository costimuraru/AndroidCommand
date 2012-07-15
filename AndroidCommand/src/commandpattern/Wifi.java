package commandpattern;

import observerpattern.Logger;

public class Wifi {
	private Logger logger;
	private boolean isOn;

	public Wifi(Logger logger) {
		this.logger = logger;
	}

	public void on() {
		if (isOn()) {
			logger.addMessage("Wifi is already on!");
			return;
		}
		isOn = true;
		logger.addMessage("Turned wifi ON.");
	}

	public void off() {
		if (!isOn()) {
			logger.addMessage("Wifi is already off!");
			return;
		}
		isOn = false;
		logger.addMessage("Turned wifi OFF.");
	}

	private boolean isOn() {
		return isOn;
	}
}
