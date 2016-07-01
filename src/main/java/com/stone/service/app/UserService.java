package com.stone.service.app;


import com.stone.service.ConfigLoader;
import com.stone.service.redis.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by young on 16-2-24.
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private ConfigLoader configLoader;
    @Autowired
    private RedisCacheService redisCacheService;

    @Transactional
    public void query() {
        LOGGER.info("From Zookeeper Data: {}", configLoader.getMsg());
        LOGGER.info("From cache Data: {}", redisCacheService.get("chenyang"));
    }

}