package Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateOperations {
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public String extractDate(LocalDateTime dateTime) {
		 StringBuilder sb = new StringBuilder()
				.append(dateTime.getYear())
				.append("-");
		 if(dateTime.getMonthValue()<10)sb.append("0");
		 sb.append(dateTime.getMonthValue());
		 sb.append("-");
		 if(dateTime.getDayOfMonth()<10)sb.append("0");
		 sb.append(dateTime.getDayOfMonth());
		 return sb.toString();
	}
	
	
	public LocalDateTime getBeggingOfNextDay(LocalDateTime dateTime) {
		LocalDateTime tomorrow = dateTime.plusDays(1);
		return LocalDateTime.of(tomorrow.getYear(), tomorrow.getMonthValue(), tomorrow.getDayOfMonth(), 0, 0);
	}
	
	public String formatDateTime(LocalDateTime dateTime) {
		return dateTime.format(dateTimeFormatter);
	}
	
	public LocalDateTime parseLocalDateTime(String dateTime){
		return LocalDateTime.parse(dateTime,dateTimeFormatter);
	}
	
}
