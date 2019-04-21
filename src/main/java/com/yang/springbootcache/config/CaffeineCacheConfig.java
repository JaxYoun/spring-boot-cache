package com.yang.springbootcache.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang
 * @date: 2019/4/21 23:03
 * @description: guava-cache配置
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        List<String> nameList = new ArrayList() {{
            add("caffeineUser");
        }};
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(nameList);
        cacheManager.setCacheLoader(this.cacheLoader());
        return cacheManager;
    }

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object o) throws Exception {
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Nullable
            @Override
            public Object reload(@NonNull Object key, @NonNull Object oldValue) throws Exception {
                return oldValue;
            }
        };
    }

}
