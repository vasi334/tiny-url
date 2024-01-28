package com.tiny_url.hashservice.controller;

import com.tiny_url.hashservice.service.HashRetrieveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hash")
public class HashController {

    private final HashRetrieveService hashRetrieveService;

    public HashController(HashRetrieveService hashRetrieveService){
        this.hashRetrieveService = hashRetrieveService;
    }

    @GetMapping("/retrieve")
    public Map<String, Object> retrieveOneHash(){
        Map<String, Object> res = new HashMap<>();

        String hash = hashRetrieveService.retrieveOne();

        res.put("data", hash);

        return res;
    }

    @GetMapping("/{hash}")
    public Map<String, Object> markAsUsed(@PathVariable("hash") String hash){
        Map<String, Object> res = new HashMap<>();

        hashRetrieveService.markHashAsUsed(hash);

        res.put("response", 200);

        return res;
    }

    @GetMapping("/unused/{hash}")
    public Map<String, Object> markAsUnused(@PathVariable("hash") String hash){
        Map<String, Object> res = new HashMap<>();

        hashRetrieveService.markHashAsUnused(hash);

        res.put("response", 200);

        return res;
    }

    @GetMapping("/")
    public String test(){
        return "hash service";
    }
}
