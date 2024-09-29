package com.example.danggunmarket.common.jwt;

import com.example.danggunmarket.common.auth.LoggedInMember;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = parseToken(header);

        if (token.equals("none")) {
            return;
        }

        Claims claims = jwtUtil.parse(token);

        String email = claims.get("email", String.class);

        LoggedInMember loggedInMember = (LoggedInMember) userDetailsService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loggedInMember.getUsername(), null, loggedInMember.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        response.addHeader("Authorization", "Bearer " + token);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();

        final String[] excludePath = {"/v1/members", "/v1/login"};

        return Arrays.stream(excludePath).anyMatch(uri::startsWith);
    }

    private String parseToken(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return "none";
        }

        return header.split(" ")[1];
    }
}
