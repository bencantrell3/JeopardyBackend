package com.example.JeopardyBackend.service;

import com.example.JeopardyBackend.model.Question;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MySQLDataBaseModification {

    private final String jdbcUrl = "jdbc:mysql://root:ekdQnnewAOMDeiLorYfoYDDjGEwrWKar@switchyard.proxy.rlwy.net:37428/railway";
    private final String username = "root";
    private final String password = "ekdQnnewAOMDeiLorYfoYDDjGEwrWKar";

    public Question getQuestion(int gameId, int questionId) {
        System.out.println("PARAMETERS RECEIVED: " + gameId + ", " + questionId);
        String query = "SELECT * FROM questions WHERE game_id = ? AND question_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, questionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Question(
                            gameId,
                            questionId,
                            resultSet.getString("category"),
                            resultSet.getString("question"),
                            resultSet.getString("answer"),
                            resultSet.getInt("points")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no question is found
    }

    public List<Question> getAllQuestions(Integer gameId) {
        System.out.println("Fetching all questions for game ID: " + gameId);
        String query = "SELECT * FROM questions WHERE game_id = ? ORDER BY question_id ASC";

        List<Question> questions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, gameId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    questions.add(new Question(
                            gameId,
                            resultSet.getInt("question_id"),
                            resultSet.getString("category"),
                            resultSet.getString("question"),
                            resultSet.getString("answer"),
                            resultSet.getInt("points")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions; // Return the list of questions
    }

    public List<Question> getAllQuestions(int gameId) {
        System.out.println("Fetching all questions for game ID: " + gameId);
        String query = "SELECT question_id, category, question, answer, points FROM questions WHERE game_id = ? ORDER BY question_id ASC";

        List<Question> questions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, gameId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    questions.add(new Question(
                            gameId,
                            resultSet.getInt("question_id"),
                            resultSet.getString("category"),
                            resultSet.getString("question"),
                            resultSet.getString("answer"),
                            resultSet.getInt("points")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching questions: " + e.getMessage());
            e.printStackTrace();
        }

        return questions; // Returns empty list if no questions found
    }

    public boolean addQuestion(int gameId, int questionId, String category, String question, String answer, int points) {
        String query = "INSERT INTO questions (game_id, question_id, category, question, answer, points) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, questionId);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, question);
            preparedStatement.setString(5, answer);
            preparedStatement.setInt(6, points);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("Question added successfully.");
                return true;
            } else {
                System.out.println("Failed to add question.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addAllQuestions(Question[] questions) {
        for (Question q : questions) {
            boolean success = addQuestion(q.getGameId(), q.getQuestionId(), q.getCategory(),
                    q.getQuestion(), q.getAnswer(), q.getPoints());
            if (!success) {
                System.out.println("Failed to add question with ID: " + q.getQuestionId());
            }
        }
    }


    public boolean deleteQuestions(int gameId) {
        String query = "DELETE FROM questions WHERE game_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, gameId);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Returns true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
