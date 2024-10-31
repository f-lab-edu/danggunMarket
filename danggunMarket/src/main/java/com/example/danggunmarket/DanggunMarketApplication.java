package com.example.danggunmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DanggunMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanggunMarketApplication.class, args);
    }

}
