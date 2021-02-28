package Settings;

public enum Intervals {
M1(1),M5(5),M15(15),M30(30),H1(60),H4(240);

private int intervalMinutes;	

Intervals(int intervalMinutes) {
	this.intervalMinutes = intervalMinutes;
	// TODO Auto-generated constructor stub
}

public int getInterval() {
	return intervalMinutes;
}
}
