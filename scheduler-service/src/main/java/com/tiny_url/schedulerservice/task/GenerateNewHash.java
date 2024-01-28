package com.tiny_url.schedulerservice.task;

import com.tiny_url.schedulerservice.model.HashEntity;
import com.tiny_url.schedulerservice.repository.HashRepository;
import com.tiny_url.schedulerservice.repository.UsedHashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateNewHash {

    private static final Logger log = LoggerFactory.getLogger(GenerateNewHash.class);

    private final HashRepository hashRepository;

    private final UsedHashRepository usedHashRepository;

    public GenerateNewHash(HashRepository hashRepository, UsedHashRepository usedHashRepository) {
        this.hashRepository = hashRepository;
        this.usedHashRepository = usedHashRepository;
    }

    @Scheduled(fixedRate = 600000)
    public void ingestHashes() {
        log.info("hash ingestion start");
        long count = hashRepository.count();
        log.info("count of available hash: "+count);
        if(count > 1000){
            log.info("Count > 1000 skip generation");
            return;
        }
        for (int i = 0; i < 200; i++) {
            String hash = generateOne();
            // a generated hash is unique if it is not in both collection
            boolean isUnique = !hashRepository.existsById(hash) && !usedHashRepository.existsById(hash);
            if(isUnique){
                hashRepository.save(new HashEntity(hash));
                log.info("ingested " + hash);
            }
        }
    }

    private String generateOne(){
        int length = 6;
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if(random.nextBoolean()){
                int randInt = random.nextInt(10);
                builder.append(randInt);
            }
            else{
                int randChar = 'a' + (int)
                        (random.nextFloat() * ('z' - 'a' + 1));
                builder.append((char) randChar);
            }
        }
        return builder.toString();
    }
}
