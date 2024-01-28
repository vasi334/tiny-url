package com.tiny_url.hashservice.service;

import com.mongodb.client.MongoCollection;
import com.tiny_url.hashservice.model.HashEntity;
import com.tiny_url.hashservice.model.UsedHashEntity;
import com.tiny_url.hashservice.repository.HashRepository;
import com.tiny_url.hashservice.repository.UsedHashRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class HashRetrieveService {

    private final HashRepository hashRepository;

    private final UsedHashRepository usedHashRepository;

    private final MongoTemplate mongoTemplate;

    public HashRetrieveService(HashRepository hashRepository, UsedHashRepository usedHashRepository, MongoTemplate mongoTemplate) {
        this.hashRepository = hashRepository;
        this.usedHashRepository = usedHashRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * method that retrieves a random hash from unused db, removes it, and adds it to used hash
     *
     * @return hash
     */
    @Transactional
    public String retrieveOne() {
        log.info("start to retrieve a hash");
        MongoCollection<Document> unusedhash = mongoTemplate.getCollection("unusedhash");
        Document hashDoc = unusedhash.findOneAndDelete(new BsonDocument());
        if (hashDoc == null) {
            // there is no hash available
            log.error("failed to retrieve a hash");
            return null;
        }
        String retrievedHash = hashDoc.get("_id", String.class);
        log.info("retrieved hash: " + retrievedHash);
        usedHashRepository.save(new UsedHashEntity(retrievedHash));
        return retrievedHash;
    }

    @Transactional
    public void markHashAsUsed(String hash){
        log.info("start to mark a hash as used");

        if(hashRepository.existsById(hash)){
            hashRepository.deleteById(hash);
        }

        usedHashRepository.save(new UsedHashEntity(hash));
    }

    @Transactional
    public void markHashAsUnused(String hash){
        log.info("start to mark a hash as unused");

        hashRepository.save(new HashEntity(hash));

        usedHashRepository.deleteById(hash);
    }
}