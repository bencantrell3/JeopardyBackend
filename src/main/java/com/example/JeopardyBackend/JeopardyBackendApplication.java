package com.example.JeopardyBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JeopardyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeopardyBackendApplication.class, args);
    }
}
