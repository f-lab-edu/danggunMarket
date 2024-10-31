package com.example.danggunmarket.product.interceptor;

import com.example.danggunmarket.common.auth.LoggedInMember;
import com.example.danggunmarket.product.ProductService;
import com.example.danggunmarket.product.exception.InValidProductIdException;
import com.example.danggunmarket.product.exception.NotAuthorizedProductException;
import com.example.danggunmarket.product.exception.ProductErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriTemplate;

@Component
@RequiredArgsConstructor
public class ValidAuthorizeProductInterceptor implements HandlerInterceptor {
    private final ProductService productService;
    private final String pattern = "/v1/products/{productId}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        if (!method.equals(HttpMethod.PUT.name()) && !method.equals(HttpMethod.DELETE.name())) {
            return true;
        }

        UriTemplate uriTemplate = new UriTemplate(pattern);
        if (!uriTemplate.matches(request.getRequestURI()))
            return true;

        String productId = uriTemplate.match(request.getRequestURI()).get("productId");
        try {
            long id = Long.parseLong(productId);

            LoggedInMember member = (LoggedInMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!productService.matchSellerByUser(id, member))
                throw new NotAuthorizedProductException(ProductErrorCode.NOT_AUTHORIZED_PRODUCT);

        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InValidProductIdException(ProductErrorCode.NOT_VALID_ID);
        }

        return true;
    }
}
