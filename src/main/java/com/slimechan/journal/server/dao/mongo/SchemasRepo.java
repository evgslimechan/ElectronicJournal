package com.slimechan.journal.server.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.slimechan.journal.server.models.schedule.ScheduleSchema;
import com.slimechan.journal.server.models.schedule.Week;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemasRepo extends MongoRepository<ScheduleSchema, Integer>{

	@Query("{'_id': ?0, 'weekType': ?1 }")
	List<ScheduleSchema> getScheduleByGroup(int group, Week type);
	
}
