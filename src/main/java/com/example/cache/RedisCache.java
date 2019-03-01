package com.example.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-28 16:46
 **/
@Component
public class RedisCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    public Object get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(Object key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    public void setExpire(Object key, long time, TimeUnit unit) {
        redisTemplate.expire(key, time, unit);
    }

    public void remove(String cacheId) {
        Set<Object> keys = redisTemplate.keys(cacheId);
        LOGGER.info("删除集合:" + keys.iterator());
        LOGGER.info("删除集合大小:" + keys.size());
        redisTemplate.delete(keys);
    }
}
