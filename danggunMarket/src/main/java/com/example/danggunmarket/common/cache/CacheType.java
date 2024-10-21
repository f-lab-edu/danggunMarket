package com.example.danggunmarket.common.cache;

import lombok.Getter;

@Getter
public enum CacheType {
    Main_PAGE("mainPage", 1, 1000);

    CacheType(String cacheName, int expireAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }

    private final String cacheName;
    private final int expireAfterWrite;
    private final int maximumSize;
}
