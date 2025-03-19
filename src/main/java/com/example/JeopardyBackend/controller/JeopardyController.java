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
    public ResponseEntity<String> addQuestion(@RequestParam int gameId,
                                              @RequestParam int questionId,
                                              @RequestParam String category,
                                              @RequestParam String question,
                                              @RequestParam String answer,
                                              @RequestParam int points) {
        // Log the incoming parameters
        System.out.println("Adding question with parameters: gameId=" + gameId
                + ", questionId=" + questionId
                + ", category=" + category
                + ", question=" + question
                + ", answer=" + answer
                + ", points=" + points);

        // Call the service method to add the question to the database
        boolean isAdded = db.addQuestion(gameId, questionId, category, question, answer, points);

        // Return appropriate response
        if (isAdded) {
            return ResponseEntity.ok("Question added successfully!");
        } else {
            return ResponseEntity.status(500).body("Error adding question.");
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id, @RequestBody String updatedQuestion) {
        // Logic to update a question
        return ResponseEntity.ok("Question with ID " + id + " updated successfully!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteQuestions(@RequestParam int gameId) {
        boolean isDeleted = db.deleteQuestions(gameId);

        if (isDeleted) {
            return ResponseEntity.ok("All questions with game ID " + gameId + " deleted successfully!");
        } else {
            return ResponseEntity.status(500).body("Error deleting questions for game ID " + gameId);
        }
    }


}
