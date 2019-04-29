package com.slimechan.journal.server.models.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.slimechan.journal.server.models.users.Group;

public class Schedule {
	
	private Map<Date, Day> week;
	
	public Schedule(Group group) {}
	
	// loading lessons
	
	/* DAY BLOCK
	public Day getToday(Date date) {
		return schedule.get(WeekDay.getByDate(date));
	}
	public void addChanges(Date date, Day day) {
		schedule.put(WeekDay.getByDate(date));
	}*/
	
	// lessons getting 
	
	public int getLesson(Date date) {
		
		for(int id = 1; id<=7;id++) {
			if(inInterval(date, id)) return id;
		}
		
		
		return -1;
	}
	
	
	public static int getWeek(Date d) {
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(d);     
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	private boolean inInterval(Date date, int lessonId) {
		Date after,before;
		switch (lessonId) {
		case 1:
			after = DateFactory.make().setHours(8).setMinutes(30).build();
			before = DateFactory.make().setHours(10).setMinutes(0).build();
			return date.after(after)&date.before(before);
		case 2:
			after = DateFactory.make().setHours(10).setMinutes(10).build();
			before = DateFactory.make().setHours(11).setMinutes(40).build();
			return date.after(after)&date.before(before);
		case 3:
			after = DateFactory.make().setHours(11).setMinutes(50).build();
			before = DateFactory.make().setHours(13).setMinutes(40).build();
			return date.after(after)&date.before(before);
		case 4:
			after = DateFactory.make().setHours(13).setMinutes(50).build();
			before = DateFactory.make().setHours(15).setMinutes(20).build();
			return date.after(after)&date.before(before);
		case 5:
			after = DateFactory.make().setHours(15).setMinutes(30).build();
			before = DateFactory.make().setHours(17).setMinutes(0).build();
			return date.after(after)&date.before(before);
		case 6:
			after = DateFactory.make().setHours(17).setMinutes(10).build();
			before = DateFactory.make().setHours(18).setMinutes(30).build();
			return date.after(after)&date.before(before);
		case 7:
			after = DateFactory.make().setHours(18).setMinutes(35).build();
			before = DateFactory.make().setHours(19).setMinutes(55).build();
			return date.after(after)&date.before(before);
		}
		return false;
	}
	// New week starts at saturday
	private void generateNextWeek(ScheduleSchema sch) {
		week = new HashMap<>();
	}
}
