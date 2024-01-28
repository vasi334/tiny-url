package com.tiny_url.schedulerservice.task;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.tiny_url.schedulerservice.model.HashEntity;
import com.tiny_url.schedulerservice.repository.HashRepository;
import com.tiny_url.schedulerservice.repository.UrlRepository;
import com.tiny_url.schedulerservice.repository.UsedHashRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ExpireHash {
    private final HashRepository hashRepository;

    private final UsedHashRepository usedHashRepository;

    private final UrlRepository urlRepository;

    private final MongoTemplate mongoTemplate;

    public ExpireHash(HashRepository hashRepository, UsedHashRepository usedHashRepository, UrlRepository urlRepository, MongoTemplate mongoTemplate) {
        this.hashRepository = hashRepository;
        this.usedHashRepository = usedHashRepository;
        this.urlRepository = urlRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "CET")
    @Transactional
    public void expireHash(){
        log.info("expire hash start");

        MongoCollection<Document> collection = mongoTemplate.getCollection("url_map");
        FindIterable<Document> documents = collection.find();

        for(Document doc:documents){
            String hash = doc.getString("_id");
            if(!urlRepository.existsById("_id")){
                continue;
            }

            LocalDateTime expireDate = urlRepository.findById(hash).get().getExpireDate();
            if(expireDate.isBefore(LocalDateTime.now())){
                log.info(hash+ " is expired and will be recycled");
                urlRepository.deleteById(hash);

                usedHashRepository.deleteById(hash);

                hashRepository.save(new HashEntity(hash));
            }
        }
    }
}
