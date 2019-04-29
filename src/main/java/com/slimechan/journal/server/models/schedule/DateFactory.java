package com.slimechan.journal.server.models.schedule;

import java.util.Date;

public class DateFactory {

	private Date date;
	
	private DateFactory() {
		date = new Date();
	}
	
	public static DateFactory make() {
		return new DateFactory();
	}
	public DateFactory setHours(int hours) {date.setHours(hours);return this;}
	public DateFactory setMinutes(int minutes) {date.setMinutes(minutes);return this;}
	public Date build() {return this.date;}
}
