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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ValidAuthorizeProductInterceptor implements HandlerInterceptor {
    private final ProductService productService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();

        if (!method.equals(HttpMethod.POST.name()) && !method.equals(HttpMethod.DELETE.name())) {
            return true;
        }

        UriComponents uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).build();
        long id;

        try {
            id = Long.parseLong(uri.getPathSegments().get(2));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new InValidProductIdException(ProductErrorCode.NOT_VALID_ID);
        }

        LoggedInMember member = (LoggedInMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!productService.matchSellerByUser(id, member))
            throw new NotAuthorizedProductException(ProductErrorCode.NOT_AUTHORIZED_PRODUCT);

        return true;
    }
}
