package DataModels;

import java.time.LocalDateTime;

public class HighestVolumeArea {
	public LocalDateTime openTime;
	public LocalDateTime closeTime;
	public double low;
	public double high;
	
	public HighestVolumeArea(LocalDateTime openTime, LocalDateTime closeTime, double low, double high) {
		super();
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.low = low;
		this.high = high;
	}
	
}
