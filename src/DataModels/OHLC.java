package DataModels;

import java.time.LocalDateTime;

public class OHLC {
public LocalDateTime openTime;
public double open;
public double high;
public double low;
public double close;

public OHLC(LocalDateTime openTime, double open, double high, double low, double close) {
	super();
	this.openTime = openTime;
	this.open = open;
	this.high = high;
	this.low = low;
	this.close = close;
}





}
