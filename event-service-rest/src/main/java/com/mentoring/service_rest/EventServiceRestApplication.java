package com.mentoring.service_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.mentoring.service_messaging", "com.mentoring.service_rest", "com.mentoring.service_impl"})
@EnableJpaRepositories(basePackages = "com.mentoring.service_impl")
@EntityScan("com.mentoring.service_dto.model")
public class EventServiceRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventServiceRestApplication.class, args);
    }
}
