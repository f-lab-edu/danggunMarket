package com.example.danggunmarket.common.config;

import com.example.danggunmarket.product.interceptor.ValidAuthorizeProductInterceptor;
import com.example.danggunmarket.product.interceptor.ValidProductIdInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final ValidProductIdInterceptor validProductIdInterceptor;
    private final ValidAuthorizeProductInterceptor validAuthorizeProductInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validProductIdInterceptor)
                .addPathPatterns("/v1/products/**");

        registry.addInterceptor(validAuthorizeProductInterceptor)
                .addPathPatterns("/v1/products/**");
    }
}
