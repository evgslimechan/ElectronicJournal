package com.slimechan.journal.server.dao.mongo;

import com.slimechan.journal.server.models.users.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends MongoRepository<Role, Long>{

    @Query("{'name':?0}")
    public Role findByName(String name);

}
