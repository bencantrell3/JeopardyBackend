package com.example.JeopardyBackend;

import com.example.JeopardyBackend.model.Question;
import com.example.JeopardyBackend.service.MySQLDataBaseModification;
import com.example.JeopardyBackend.service.QuestionProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JeopardyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeopardyBackendApplication.class, args);
    }
}
