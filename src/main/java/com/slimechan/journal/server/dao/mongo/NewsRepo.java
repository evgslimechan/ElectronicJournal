package com.slimechan.journal.server.dao.mongo;

import com.slimechan.journal.server.models.news.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends MongoRepository<News, Integer> {

}
