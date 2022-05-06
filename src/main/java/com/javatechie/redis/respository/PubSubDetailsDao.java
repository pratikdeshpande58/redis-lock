package com.javatechie.redis.respository;

import com.javatechie.redis.entity.PubSubDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Repository
public class PubSubDetailsDao extends Thread{

    public static final String HASH_KEY = "PubSubDetails";
    private  CountDownLatch countDownlatch;
    @Autowired
    private RedisTemplate template;

    public Boolean save(PubSubDetails pubSubDetails){
        PubSubDetails pu2 = new PubSubDetails(1234,"isolation2","comp");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1");
                template.opsForHash().put(HASH_KEY,pubSubDetails.getId(),pubSubDetails);
//        System.out.println(value);
//        return value;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                template.opsForHash().put(HASH_KEY, pu2.getId(),pu2);
//        System.out.println(value);
//        return value;
            }
        }).start();
        //template.opsForHash().put(HASH_KEY,pubSubDetails.getId(),pubSubDetails);
//        Boolean value = template.opsForHash().putIfAbsent(HASH_KEY,pubSubDetails.getId(),pubSubDetails);
//        System.out.println(value);
//        return value;


        return null;
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
