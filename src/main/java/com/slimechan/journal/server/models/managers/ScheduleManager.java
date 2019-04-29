package com.slimechan.journal.server.models.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slimechan.journal.server.dao.mongo.SchemasRepo;
import com.slimechan.journal.server.models.schedule.ScheduleSchema;
import com.slimechan.journal.server.models.schedule.Week;

@Service
public class ScheduleManager {

	@Autowired private SchemasRepo schemRepo;
	
	private Map<Week, ScheduleSchema> defaultSchemas;
	
	private ScheduleManager() {
		defaultSchemas = new HashMap<Week, ScheduleSchema>();
	}
	
	
	
	// TODO: add loading from DB
	private void loadSchemas() {}
	
	public ScheduleSchema getSchema(int group, Week weekType) {
		List<ScheduleSchema> s = schemRepo.getScheduleByGroup(group, weekType);
		if(s.isEmpty()) return null; else
		if(s.size()>=1) return s.get(0); else return null;
	}
	
	public void addSchema(ScheduleSchema sch) {
		schemRepo.save(sch);
	}
}
