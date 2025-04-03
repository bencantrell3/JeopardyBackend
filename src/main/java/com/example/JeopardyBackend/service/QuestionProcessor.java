package com.example.JeopardyBackend.service;

import com.example.JeopardyBackend.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

public class QuestionProcessor {
    public static Question[] parseQuestions(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Question[] questions = objectMapper.readValue(jsonString, Question[].class);
            if (questions.length != 25) {
                throw new IllegalArgumentException("Expected 25 questions but got " + questions.length);
            }
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            return new Question[0]; // Return an empty array if parsing fails
        }
    }
}