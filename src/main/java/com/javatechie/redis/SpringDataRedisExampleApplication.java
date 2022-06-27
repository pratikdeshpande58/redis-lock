package com.javatechie.redis;

import com.javatechie.redis.entity.PubSubDetails;
import com.javatechie.redis.respository.PubSubDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@RestController
@RequestMapping("/pubSubDetails")
public class SpringDataRedisExampleApplication {
    @Autowired
    private PubSubDetailsDao dao;


    public Boolean save(@RequestBody PubSubDetails pubSubDetails) {
        return dao.save(pubSubDetails);
    }

    @GetMapping
    public List<PubSubDetails> getAllPubSubMessages() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public PubSubDetails findPubSubMessage(@PathVariable int id) {
        return dao.findPubSubMessage(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id)   {
    	return dao.deletePubSubMessage(id);
	}


    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisExampleApplication.class, args);
    }
}
