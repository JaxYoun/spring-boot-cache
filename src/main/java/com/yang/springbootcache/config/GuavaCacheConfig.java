package com.yang.springbootcache.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author: Yang
 * @date: 2019/4/21 23:03
 * @description: guava-cache配置
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(
                CacheBuilder
                .newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(100));
        return cacheManager;
    }

}
