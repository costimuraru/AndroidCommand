package commandpattern;

import java.util.Stack;

/**
 * @author Costi
 * 
 */

public class RemoteControl {
	public static final int LEFT_BTN = 1, RIGHT_BTN = 2;
	private int slotCount;
	private RemoteControlSlot[] slots;
	private Stack<Command> performedCommand;

	public RemoteControl(int slotCount) {
		if (slotCount < 0)
			throw new IllegalArgumentException();
		this.slotCount = slotCount;
		this.slots = new RemoteControlSlot[slotCount];
		this.performedCommand = new Stack<Command>();

		// Fill with empty slots instead of null objects.
		for (int i = 0; i < slotCount; i++) {
			slots[i] = new RemoteControlSlot();
		}
	}

	/**
	 * Set on and off commands to the specified slot.
	 * 
	 * @param slot
	 *            The remote control slot.
	 * @param onCommand
	 *            The on command.
	 * @param offCommand
	 *            The off command.
	 */
	public void setCommand(int slot, String name, Command onCommand,
			Command offCommand) {
		slots[slot] = new RemoteControlSlot(name, onCommand, offCommand);
	}

	/**
	 * Push a button on the remote control.
	 * 
	 * @param slot
	 *            The slot
	 * @param button
	 *            The left or right button on the specified slot.
	 */
	public void pushButton(int slotIndex, int button) {
		Command command = null;
		if (button == LEFT_BTN) {
			command = slots[slotIndex].getOnCommand();
		} else if (button == RIGHT_BTN) {
			command = slots[slotIndex].getOffCommand();
		} else
			throw new IllegalArgumentException();

		command.execute();
		performedCommand.push(command);
	}

	/**
	 * Undo the last command.
	 */
	public void undo() {
		if (performedCommand.isEmpty())
			return;
		performedCommand.pop().undo();
	}

	public RemoteControlSlot getSlot(int slotIndex) {
		return slots[slotIndex];
	}

	public int getSlotCount() {
		return slotCount;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("---- Remote Control ----\n");
//		for (int i = 0; i < onCommands.length; i++) {
//			String[] split = onCommands[i].getClass().getName().split("\\.");
//			String onName = split[split.length - 1];
//			split = offCommands[i].getClass().getName().split("\\.");
//			String offName = split[split.length - 1];
//
//			builder.append("Slot " + i + " : " + onName + " - " + offName
//					+ "\n");
//		}
		builder.append("------------------------\n");
		return builder.toString();
	}
}
