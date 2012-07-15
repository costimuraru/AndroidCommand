package ph.androidcommand;

import java.util.Observable;
import java.util.Observer;

import commandpattern.RemoteControl;
import commandpattern.RemoteControlSlot;
import commandpattern.Wifi;
import commandpattern.WifiOffCommand;
import commandpattern.WifiOnCommand;

import observerpattern.Logger;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AndroidCommandActivity extends Activity implements Observer {
	private Logger logger;
	private RemoteControl remoteControl;
	private final static int NR_SLOTS = 5, NR_LOG_MSGS = 7;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		logger = new Logger(NR_LOG_MSGS);
		logger.addObserver(this);

		createRemoteControl();
		displayRemoteControl();
	}

	private void createRemoteControl() {
		remoteControl = new RemoteControl(NR_SLOTS);
		Wifi wifi = new Wifi(logger);
		remoteControl.setCommand(0, "Wifi", new WifiOnCommand(wifi),
				new WifiOffCommand(wifi));
		remoteControl.setCommand(1, "Gps signal", new WifiOnCommand(wifi),
				new WifiOffCommand(wifi));
	}

	private void displayRemoteControl() {
		TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
		for (int i = 0; i < remoteControl.getSlotCount(); i++) {
			final int slotIndex = i;
			RemoteControlSlot slot = remoteControl.getSlot(i);
			if (slot.noCommand())
				continue;

			TableRow row = (TableRow) getLayoutInflater().inflate(
					R.layout.remote_slot, null);

			// Set name
			TextView tv = (TextView) row.findViewById(R.id.slot_name_tv);
			tv.setText(slot.getName());

			// Set on btn listener.
			Button button = (Button) row.findViewById(R.id.on_btn);
			button.setOnClickListener(new View.OnClickListener() {

				public void onClick(View arg0) {
					remoteControl.pushButton(slotIndex, RemoteControl.LEFT_BTN);
				}
			});
			// Set off btn listener.
			button = (Button) row.findViewById(R.id.off_btn);
			button.setOnClickListener(new View.OnClickListener() {

				public void onClick(View arg0) {
					remoteControl
							.pushButton(slotIndex, RemoteControl.RIGHT_BTN);
				}
			});

			table.addView(row);
		}

	}

	public void update(Observable observable, Object data) {
		if (observable instanceof Logger)
			// Logger message changed.
			((TextView) findViewById(R.id.logger_tv)).setText(logger
					.getMessage());
	}

	public void undoClick(View view) {
		if (remoteControl == null)
			return;
		remoteControl.undo();
	}
}