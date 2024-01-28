package com.tiny_url.schedulerservice.repository;

import com.tiny_url.schedulerservice.model.HashEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashRepository extends MongoRepository<HashEntity, String> {
}
