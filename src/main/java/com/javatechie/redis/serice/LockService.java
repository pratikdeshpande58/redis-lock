package com.javatechie.redis.serice;

public interface LockService {
    String lock() throws InterruptedException;
    void failLock();
    String properLock();
}