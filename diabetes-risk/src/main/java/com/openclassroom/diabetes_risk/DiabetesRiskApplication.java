package com.openclassroom.diabetes_risk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DiabetesRiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiabetesRiskApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .basicAuthentication("docteur_admin", "mediscreen2026")
                .build();
    }
}