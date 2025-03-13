package com.example.JeopardyBackend.service;

import com.example.JeopardyBackend.model.Question;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class MySQLDataBaseModification {

    private final String jdbcUrl = "jdbc:mysql://root:ekdQnnewAOMDeiLorYfoYDDjGEwrWKar@switchyard.proxy.rlwy.net:37428/railway";
    private final String username = "root";
    private final String password = "ekdQnnewAOMDeiLorYfoYDDjGEwrWKar";

    // Method to fetch a Question by gameId and questionId, and return it as a string
    public String getQuestion(int gameId, int questionId) {
        String query = "SELECT * FROM questions WHERE game_id = ? AND question_id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, questionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve data from the result set
                    String category = resultSet.getString("category");
                    String questionText = resultSet.getString("question");
                    String answer = resultSet.getString("answer");

                    // Create and return the Question as a string
                    Question question = new Question(gameId, questionId, category, questionText, answer);
                    System.out.println(question.toString());
                    return question.toString();
                } else {
                    return "Question not found for the given gameId and questionId.";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error retrieving the question: " + e.getMessage();
        }
    }
}
