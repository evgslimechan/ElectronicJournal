package com.slimechan.journal.server.models.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slimechan.journal.server.models.marks.MarkList;
import com.slimechan.journal.server.models.users.Group;

public class Day {

	private int lessonId;
	
	private Date date;
	
	// subject must store in right order for lessonId work
	private Map<Subject, MarkList> lessons;
	
	protected Day(List<Subject> subjects, Date date) {
		lessons = new HashMap<Subject, MarkList>();
		for(Subject s : subjects) lessons.put(s, null);
	}
	
	
	// changes manipulation
	
	public void addChanges(int lesson, Subject s) {
		Map<Subject, MarkList> newLess = new HashMap<Subject, MarkList>(); 
		
		for(int id = 0; id<7;id++) {
			if(id==lesson) {
				newLess.put(s, null);
			}else {
				newLess.put(getLesson(id), lessons.get(getLesson(id)));
			}			
		}
		lessons = newLess;
	}
	
	// marks block
	protected void createAllMarkLists(Group g) {
		for(int id = 0; id<7;id++) {
			createMarkList(id, g);
		}
	}
	protected void createMarkList(int lesson, Group g) {
		Subject s = getLesson(lesson);
		if(s!=null) {
			lessons.replace(s, new MarkList(g.getStudents()));
		}
	}
	// 1-7
	public Subject getLesson(int id) {
		return (Subject) lessons.keySet().toArray()[id-1];
	}
}
