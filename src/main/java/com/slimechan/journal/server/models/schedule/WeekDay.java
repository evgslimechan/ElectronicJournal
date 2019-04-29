package com.slimechan.journal.server.models.schedule;

import java.util.Date;

public enum WeekDay {
	Sunday,
	Monday,
	Tuesday,
	Wednesday,
	Thursday,
	Friday,
	Saturday;
	
	public static WeekDay getByDate(Date d) {
		return WeekDay.values()[d.getDay()];
	}
	public static WeekDay getByName(String id) {
		return WeekDay.valueOf(id);
	}
}
