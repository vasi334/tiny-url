package com.tiny_url.hashservice.repository;

import com.tiny_url.hashservice.model.UsedHashEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedHashRepository extends MongoRepository<UsedHashEntity, String> {
}
