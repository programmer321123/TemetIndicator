package TemetScanner;

import DataModels.TemetIndicator;

public class TemetScannerInput {
public String instrumentName;
public TemetIndicator temetIndicator;
public double price;

public TemetScannerInput(String instrumentName, TemetIndicator temetIndicator, double price) {
	super();
	this.instrumentName = instrumentName;
	this.temetIndicator = temetIndicator;
	this.price = price;
}


}
