package com.tiny_url.hashservice.repository;

import com.tiny_url.hashservice.model.HashEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashRepository extends MongoRepository<HashEntity, String> {
}
