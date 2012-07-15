package observerpattern;

import java.util.List;
import java.util.Observable;

/**
 * @author Costi This class holds the log messages in a limited queue.
 */
public class Logger extends Observable {
	private List<String> messages;

	public Logger(int nrLines) {
		messages = new LimitedQueue<String>(nrLines);
	}

	public void addMessage(String msg) {
		messages.add(msg.trim());
		setChanged();
		notifyObservers();
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		for (String message : messages)
			builder.append("> " + message + "\n");
		return builder.toString().trim();
	}
}
