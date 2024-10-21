package com.example.danggunmarket.common.cache.keyGenerator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("paginationKeyGenerator")
public class PaginationKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key = new StringBuilder();

        if (params.length == 0 || params[0] == null) {
            return "0-20";
        }

        Pageable pageable = (Pageable) params[0];

        key.append(pageable.getPageNumber())
                .append("-")
                .append(pageable.getPageSize());

        return key.toString();
    }
}
