package com.slimechan.journal.server.models.users;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import com.slimechan.journal.server.models.schedule.Day;
import com.slimechan.journal.server.models.schedule.Schedule;
import com.slimechan.journal.server.models.schedule.WeekDay;

public class Group {

	private int id;
	
	private String name;
	
	private User curator;
	
	private List<User> students;
	
	private Schedule schedule;
	
	protected Group(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	private Group(int id, String name, User teach, List<User> users) {
		
	}
	
	public int getId() {return id;}
	public String getName() {return name;}
	public User getCurator() {return curator;}
	// STUDENTS BLOCK
	public List<User> getStudents(){return students;}
	public User getUserByListId(int id) {
		if(id>0 & id<=students.size()) {
			return students.get(id-1);
		}
		return null;
	}
	

	
	protected void setCurator(User c) {this.curator = c;}
	
}
