package com.w2m.supermaintenance.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {
    @SuppressWarnings("null")
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<ConcurrentMapCache> caches = Arrays.asList(
                new ConcurrentMapCache("heroes"),
                new ConcurrentMapCache("heroe"),
                new ConcurrentMapCache("heroesByName"));

        cacheManager.setCaches(caches);

        return cacheManager;
    }
}
