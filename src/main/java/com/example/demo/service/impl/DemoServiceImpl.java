package com.example.demo.service.impl;

import com.example.cache.CacheId;
import com.example.cache.RedisCache;
import com.example.demo.dao.DemoDao;
import com.example.demo.dao.DemoRepository;
import com.example.demo.entity.Demo;
import com.example.demo.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-25 16:03
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Resource
    private DemoDao demoDao;

    @Resource
    private RedisCache redisCache;

    @Override
    public void save(Demo demo) {

        demoRepository.save(demo);
    }

    @Override
    public Demo getDemoById(long id) {
        Demo demo = (Demo) redisCache.get(CacheId.REDIS_TEST.getCacheIdByKey());
        if (demo == null) {
            demo = demoDao.getDemoById(id);
            redisCache.set(CacheId.REDIS_TEST.getCacheIdByKey(), demo);
        }
        return demo;
    }


}
