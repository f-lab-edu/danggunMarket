package com.example.danggunmarket.common.config;

import com.example.danggunmarket.common.auth.LoggedInMember;
import com.example.danggunmarket.common.jwt.JwtFilter;
import com.example.danggunmarket.common.jwt.JwtUtil;
import com.example.danggunmarket.member.MemberEntity;
import com.example.danggunmarket.member.MemberRepository;
import com.example.danggunmarket.member.exception.MemberErrorCode;
import com.example.danggunmarket.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    private final MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.csrf(CsrfConfigurer::disable);

        security.addFilterBefore(new JwtFilter(jwtUtil, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        security.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/v1/members/**", "/v1/login").permitAll();
            auth.anyRequest().authenticated();
        });

        return security.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            MemberEntity member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new MemberNotFoundException(MemberErrorCode.NOT_FOUND_MEMBER));

            return LoggedInMember.builder()
                    .id(member.getMemberId())
                    .role(member.getRole())
                    .nickname(member.getNickname())
                    .email(member.getEmail())
                    .build();
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
