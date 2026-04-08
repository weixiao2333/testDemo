package com.example.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    void set(String key, Object value);

    void set(String key, Object value, long timeout, TimeUnit unit);

    Object get(String key);

    Boolean delete(String key);

    Boolean hasKey(String key);

    void expire(String key, long timeout, TimeUnit unit);

    Long getExpire(String key);
}
