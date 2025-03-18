package com.example.JeopardyBackend.controller;

import com.example.JeopardyBackend.service.MySQLDataBaseModification;
import com.example.JeopardyBackend.model.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jeopardy")
public class JeopardyController {

    private final MySQLDataBaseModification db = new MySQLDataBaseModification();

    @GetMapping("/get")
    public String getQuestion(@RequestParam(required = false) Integer gameId,
                              @RequestParam(required = false) Integer questionId) {
        if (gameId == null || questionId == null) {
            return "Missing required query parameters: gameId and questionId.";
        }

        System.out.println("Question retrieved successfully for game ID: " + gameId + " and question ID: " + questionId);

        return db.getQuestion(gameId, questionId);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody String question) {
        // Logic to add a question
        return ResponseEntity.ok("Question added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id, @RequestBody String updatedQuestion) {
        // Logic to update a question
        return ResponseEntity.ok("Question with ID " + id + " updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        // Logic to delete a question
        return ResponseEntity.ok("Question with ID " + id + " deleted successfully!");
    }
}
