package com.tiny_url.schedulerservice.repository;

import com.tiny_url.schedulerservice.model.UsedHashEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsedHashRepository extends MongoRepository<UsedHashEntity, String> {
}
