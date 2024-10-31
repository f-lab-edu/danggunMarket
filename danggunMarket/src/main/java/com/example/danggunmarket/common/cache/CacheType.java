package com.example.danggunmarket.common.cache;

import lombok.Getter;

@Getter
public enum CacheType {
    MAIN_PAGE("mainPage", 1, 1000, ExpireType.EXPIRE_AFTER_WRITE);

    CacheType(String cacheName, int expireTime, int maximumSize, ExpireType expireType) {
        this.cacheName = cacheName;
        this.expireTime = expireTime;
        this.maximumSize = maximumSize;
        this.expireType = expireType;
    }

    private final String cacheName;
    private final int expireTime;
    private final int maximumSize;
    private final ExpireType expireType;

    public boolean isExpireAfterWrite(){
        return expireType == ExpireType.EXPIRE_AFTER_WRITE;
    }

    public boolean isAccessAfterWrite(){
        return expireType == ExpireType.ACCESS_AFTER_WRITE;
    }

    public enum ExpireType{
        EXPIRE_AFTER_WRITE,
        ACCESS_AFTER_WRITE
    }
}
