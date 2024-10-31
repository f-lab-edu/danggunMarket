package com.example.danggunmarket.common.config;

import com.example.danggunmarket.common.cache.CacheType;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = Arrays.stream(CacheType.values())
                .map(cache -> new CaffeineCache(cache.getCacheName(), setEvictionRule(cache)))
                .toList();

        cacheManager.setCaches(caches);

        return cacheManager;
    }

    private Cache<Object, Object> setEvictionRule(CacheType cacheType){
        if(cacheType.isExpireAfterWrite())
            return Caffeine.newBuilder()
                    .expireAfterWrite(cacheType.getExpireTime(), TimeUnit.SECONDS)
                    .maximumSize(cacheType.getMaximumSize())
                    .build();

        return Caffeine.newBuilder()
                .expireAfterAccess(cacheType.getExpireTime(), TimeUnit.SECONDS)
                .maximumSize(cacheType.getMaximumSize())
                .build();
    }
}
