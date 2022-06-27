package com.javatechie.redis.respository;

import com.javatechie.redis.entity.PubSubDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class PubSubDetailsDao{

    public static final String HASH_KEY = "PubSubDetails";
    @Autowired
    private RedisTemplate template;

    public Boolean save(PubSubDetails pubSubDetails){
        template.opsForHash().put(HASH_KEY,pubSubDetails.getId(),pubSubDetails);
      //Boolean value = template.opsForHash().putIfAbsent(HASH_KEY,pubSubDetails.getId(),"team");
       //System.out.println(value);
      return true;

    }

    public List<PubSubDetails> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public PubSubDetails findPubSubMessage(int id){
        System.out.println(template.opsForHash().hasKey(HASH_KEY, id));
        return (PubSubDetails) template.opsForHash().get(HASH_KEY,id);
    }


    public String deletePubSubMessage(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
