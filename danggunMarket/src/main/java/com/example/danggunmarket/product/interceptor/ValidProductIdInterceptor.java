package com.example.danggunmarket.product.interceptor;

import com.example.danggunmarket.product.ProductService;
import com.example.danggunmarket.product.exception.InValidProductIdException;
import com.example.danggunmarket.product.exception.ProductErrorCode;
import com.example.danggunmarket.product.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriTemplate;

import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ValidProductIdInterceptor implements HandlerInterceptor {
    private final ProductService productService;
    private final String[] excludeMethods = {"POST", "OPTIONS"};
    private final String pattern = "/v1/products/{productId}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        UriTemplate template = new UriTemplate(pattern);

        if (!template.matches(request.getRequestURI())) {
            return true;
        }

        String productId = template.match(request.getRequestURI()).get("productId");

        if (Arrays.asList(excludeMethods).contains(method)
                || (method.equals("GET") && productId == null)) {
            return true;
        }

        if (Objects.isNull(productId))
            return false;

        try {
            long id = Long.parseLong(productId);

            if (!productService.existProductById(id))
                throw new ProductNotFoundException(ProductErrorCode.NOT_FOUND_PRODUCT);

        } catch (NumberFormatException ex) {
            throw new InValidProductIdException(ProductErrorCode.NOT_VALID_ID);
        }

        return true;
    }
}
