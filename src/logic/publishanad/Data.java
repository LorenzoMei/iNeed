package logic.publishanad;

import java.util.Calendar;
import java.util.Calendar.Builder;

public class Data{
	private int year;
	private int month;
	private int day;
	private int hours;
	private int minutes;
	private int seconds;
	
	private Builder calendarBuilder = new Calendar.Builder();
	
	public Data() {
		Calendar calendar = Calendar.getInstance();
		
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.day = calendar.get(Calendar.DATE);
		
		this.hours = calendar.get(Calendar.HOUR);
		this.minutes = calendar.get(Calendar.MINUTE);
		this.seconds = calendar.get(Calendar.SECOND);
	}
	
	public Calendar buildDate() {
		calendarBuilder.setDate(this.year, this.month, this.day);
		return calendarBuilder.build();
	}
	
	public Calendar buildDateAndHour() {
		calendarBuilder.setDate(year, month, day);
		calendarBuilder.setTimeOfDay(hours, minutes, seconds);
		return calendarBuilder.build();
	}
}
