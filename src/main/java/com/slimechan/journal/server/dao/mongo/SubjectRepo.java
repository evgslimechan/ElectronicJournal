package com.slimechan.journal.server.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.slimechan.journal.server.models.schedule.Subject;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends MongoRepository<Subject, Integer>{

}
