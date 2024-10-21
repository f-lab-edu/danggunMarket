package com.example.danggunmarket.product.interceptor;

import com.example.danggunmarket.product.ProductService;
import com.example.danggunmarket.product.exception.InValidProductIdException;
import com.example.danggunmarket.product.exception.ProductErrorCode;
import com.example.danggunmarket.product.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ValidProductIdInterceptor implements HandlerInterceptor {
    private final ProductService productService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        UriComponents uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).build();

        if(method.equals(HttpMethod.OPTIONS.name()) ||
                (method.equals(HttpMethod.GET.name()) && uri.getPathSegments().size() < 3)){
            return true;
        }

        if(uri.getPathSegments().size() < 3)
            return false;

        long id;
        try {
            id = Long.parseLong(uri.getPathSegments().get(2));
        }catch (NumberFormatException ex){
            throw new InValidProductIdException(ProductErrorCode.NOT_VALID_ID);
        }

        if(!productService.existProductById(id))
            throw new ProductNotFoundException(ProductErrorCode.NOT_FOUND_MEMBER);

        return true;
    }
}
