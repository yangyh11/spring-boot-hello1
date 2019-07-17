package com.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-07-17 14:17
 */
@Component
public class GuavaCache implements InitializingBean {

    private Cache<Object, Object> cache;

    /**
     * 缓存大小
     **/
    private Long maxSize = 1024L;

    /**
     * 过期时间
     **/
    private Long expireTime = 1440L;
    /**
     * 时间单位
     **/
    private TimeUnit unit = TimeUnit.MINUTES;

    /**
     * 放入缓存
     **/
    public void set(Object key, Object value) {
        cache.put(key, value);
    }

    /**
     * 获取缓存
     **/
    public Object get(Object key) {
        return cache.getIfPresent(key);
    }

    /**
     * 移除缓存
     **/
    public void remove(Object key) {
        cache.invalidate(key);
    }

    /**
     * 批量移除缓存
     **/
    public void remove(List keys) {
        if (keys != null && keys.size() > 0) {
            cache.invalidateAll(keys);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = CacheBuilder.newBuilder()
                // 缓存大小设置
                .maximumSize(maxSize)
                // 过期时间，在这个时间段内没有被读/写访问，就会被回收
                .expireAfterWrite(expireTime, unit)
                .build();
    }

}
