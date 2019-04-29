package com.slimechan.journal.server.models.schedule;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.slimechan.journal.server.models.users.User;

@Document(collection="subjects")
public class Subject {

	@Id
	private int id;
	private String name;
	
	private User teacher;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	
}
