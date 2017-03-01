package com.csra;

import com.csra.camel.EmailProcessor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.zipkin.starter.CamelZipkin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@CamelZipkin
public class CamelApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CamelApplication.class);
        application.run(args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Bean
    public EmailProcessor emailProcessor() {
        EmailProcessor emailProcessor = new EmailProcessor();
        return emailProcessor;
    }
}
