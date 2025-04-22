package com.example.JeopardyBackend.controller;

import com.example.JeopardyBackend.service.MySQLDataBaseModification;
import com.example.JeopardyBackend.model.Question;
import com.example.JeopardyBackend.service.QuestionProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jeopardy")
public class JeopardyController {

    private final MySQLDataBaseModification db = new MySQLDataBaseModification();

    @GetMapping("/get")
    public ResponseEntity<?> getQuestion(@RequestParam(required = false) Integer gameId,
                                         @RequestParam(required = false) Integer questionId) {
        if (gameId == null || questionId == null) {
            return ResponseEntity.badRequest().body("Missing required query parameters: gameId and questionId.");
        }

        System.out.println("Question retrieved successfully for game ID: " + gameId + " and question ID: " + questionId);

        Question question = db.getQuestion(gameId, questionId);

        if (question == null) {
            return ResponseEntity.notFound().build(); // Return 404 if no question found
        }

        return ResponseEntity.ok(question); // Spring Boot automatically converts to JSON
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllQuestions(@RequestParam Integer gameId) {
        if (gameId == null) {
            return ResponseEntity.badRequest().body("Missing required query parameter: gameId.");
        }

        System.out.println("Retrieving all questions for game ID: " + gameId);

        List<Question> questions = db.getAllQuestions(gameId);

        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no questions found
        }

        return ResponseEntity.ok(questions); // Return all questions as JSON
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



    @PostMapping("/createquestions")
    public void receiveQuestions(@RequestBody Map<String, String> body) {
        String jsonString = body.get("questionSet");
        MySQLDataBaseModification sql = new MySQLDataBaseModification();
        Question[] questions = QuestionProcessor.parseQuestions(jsonString);
        sql.addAllQuestions(questions);
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
