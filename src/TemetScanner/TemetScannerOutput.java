package TemetScanner;

import java.util.HashMap;
import java.util.Map;

import Settings.Intervals;

public class TemetScannerOutput {
	public String instrumentName;
	// each signal can be -1 0 1 -1 market going down , 0 can't tell 1 market going up
	public Map<Intervals,Integer> signals = new HashMap<>();
	
	public TemetScannerOutput(String instrumentName, Map<Intervals, Integer> signals) {
		super();
		this.instrumentName = instrumentName;
		this.signals = signals;
	}
	

	
	
}
