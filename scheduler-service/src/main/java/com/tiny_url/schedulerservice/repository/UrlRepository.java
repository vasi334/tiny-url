package com.tiny_url.schedulerservice.repository;

import com.tiny_url.schedulerservice.model.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
