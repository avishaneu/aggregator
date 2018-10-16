package com.ut.testtasks.aggregator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = { "com.ut.testtasks.aggregator"})
public class Application {
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    
    @Bean
    public ExecutorService executor() {
        return Executors.newFixedThreadPool(100);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
