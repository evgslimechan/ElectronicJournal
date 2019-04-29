package com.slimechan.journal.server.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.slimechan.journal.server.models.users.User;

@Repository
public interface UserRepo extends MongoRepository<User, Long>{

	@Query("{'name': ?0 }")
	public User getByLogin(String login);
	
}
