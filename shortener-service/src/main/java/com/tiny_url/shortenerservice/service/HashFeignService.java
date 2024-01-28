package com.tiny_url.shortenerservice.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
@FeignClient(value = "hash-service")
public interface HashFeignService {
    @GetMapping("/hash/retrieve")
    Map<String, Object> retrieve();

    @GetMapping("/hash/{hash}")
    Map<String, Object> markHashAsUsed(@PathVariable("hash") String hash);

    @GetMapping("/hash/unused/{hash}")
    Map<String, Object> markHashAsUnUsed(@PathVariable("hash") String hash);
}