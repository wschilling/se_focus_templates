package edu.msoe.sefocus.pccmdui;

import java.util.Observable;
import java.util.Observer;

import edu.msoe.sefocus.core.interfaces.iBatteryMonitor;

/**
 * This class will act as a battery observer.  It will print out the remaining battery life to the console.
 * @author schilling
 *
 */
public class BatteryDisplay implements Observer {
	private iBatteryMonitor ov = null;

	public BatteryDisplay(iBatteryMonitor ov) {
		this.ov = ov;
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs == ov) {
			System.out.printf("%s %.2f\n", "Battery: ", ov.getBatteryLifeRemaining());
		}
	}

}
