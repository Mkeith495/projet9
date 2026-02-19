package com.sprint2.doctor_notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate; 

@SpringBootApplication
public class DoctorNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorNotesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}