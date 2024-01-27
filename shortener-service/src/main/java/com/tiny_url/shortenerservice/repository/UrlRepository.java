package com.tiny_url.shortenerservice.repository;

import com.tiny_url.shortenerservice.model.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    List<UrlEntity> findUrlEntitiesByUsername(String username);
}
