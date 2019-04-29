package com.slimechan.journal.server.models.managers;

import org.springframework.stereotype.Service;

import com.slimechan.journal.server.dao.mongo.SubjectRepo;
import com.slimechan.journal.server.models.schedule.Subject;

@Service
public class SubjectManager {

	//==========================================
	// STORE ALL DATA ABOUT ALL ACTIVE SUBJECTS
	//==========================================
	
	private SubjectRepo subjects;
	
	private SubjectManager() {
		
	}
	
	public Subject getSubjectById(int id) {
		return subjects.findById(id).get();
	}
	public void addSubject(Subject sub) {
		if(!subjects.findAll().contains(sub)) {
			subjects.insert(sub);
		}
	}
}
