package com.slimechan.journal.server.models.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="scheduleSchemas")
public class ScheduleSchema {
	
	/* +======+======+==============================+
	 * |Monday|Tuesay|								|
	 * +======+======+==============================+
	 * |subjId|      |                              |
	 * |subjId|		 |								|
	 * |null  |		 |								|
	 * +============================================+
	 */
	
	@Id
	private int group;
	@Field("weekType")
	private Week weekType;
	@Field("week")
	private Map<WeekDay, List<Integer>> week;
	
	@PersistenceConstructor
	protected ScheduleSchema(int group, Week weekType, Map<WeekDay, List<Integer>> week) {
		this.group = group;
		this.weekType = weekType;
		this.week = week;
		
	}
	
	public ScheduleSchema(Map<String, String> post) {
		Map<WeekDay, List<Integer>> wk = new HashMap<WeekDay, List<Integer>>();
		for(WeekDay d : WeekDay.values()) {
			wk.put(d, new ArrayList<Integer>());
		}
		for(String day : post.keySet()) {
			
			WeekDay d = WeekDay.getByName(day.split(":")[0].trim());
			if(wk.get(d)==null) System.out.println(day.split(":")[0]+" : "+d+" array list is null");
			wk
			.get(d)
			.add((post.get(day)!=null &
							!post.get(day).trim().equals("") )
							?Integer.parseInt(post.get(day).trim())
									:-1);

		}
		setWeek(wk);
	}

	/*private List<Subject> makeFromIds(List<Integer> ids){
		List<Subject> subjs = new ArrayList<>();
		for(int id : ids) {
			if(id==-1) subjs.add(null); 
			else subjs.add(subManager.getSubjectById(id));
		}
		return subjs;
	}*/
	private void loadSchedule(WeekDay day) {
		
	}
	
	public List<Integer> getSubjects(WeekDay day){
		return getWeek().get(day);
	}

	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	
	public Week getWeekType() {
		return weekType;
	}

	public void setWeekType(Week weekType) {
		this.weekType = weekType;
	}

	public Map<WeekDay, List<Integer>> getWeek() {
		return week;
	}

	private void setWeek(Map<WeekDay, List<Integer>> week) {
		this.week = week;
	}
	
	
}
