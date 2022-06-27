package com.javatechie.redis.serice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class MyController {
    private final LockService lockService;
    @Autowired
    LockService getLockService;

    public MyController(LockService lockService) {
        this.lockService = lockService;
    }

    @PutMapping("/lock")
    public String lock() throws InterruptedException {
         return lockService.lock();
    }

    @GetMapping("/properLock")
    public String properLock(){
        return lockService.properLock();
    }

    @PutMapping("/failLock")
    public String failLock(){
        lockService.failLock();
        return "fail lock called, output in logs";
    }
}