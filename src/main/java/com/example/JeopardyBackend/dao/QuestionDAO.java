package com.example.JeopardyBackend.dao;

import com.example.JeopardyBackend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a question
    public int addQuestion(Question question) {
        String sql = "INSERT INTO questions (game_id, category, question, answer) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, question.getGameId(), question.getCategory(), question.getQuestion(), question.getAnswer());
    }

    // Retrieve all questions
    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM questions";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Question(
                rs.getInt("game_id"),
                rs.getInt("question_id"),
                rs.getString("category"),
                rs.getString("question"),
                rs.getString("answer"),
                rs.getInt("points")
        ));
    }

    // Retrieve a question by ID
    public Question getQuestionById(int id) {
        String sql = "SELECT * FROM questions WHERE question_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Question(
                rs.getInt("game_id"),
                rs.getInt("question_id"),
                rs.getString("category"),
                rs.getString("question"),
                rs.getString("answer"),
                rs.getInt("points")
        ), id);
    }

    // Update a question
    public int updateQuestion(Question question) {
        String sql = "UPDATE questions SET game_id = ?, category = ?, question = ?, answer = ? WHERE question_id = ?";
        return jdbcTemplate.update(sql, question.getGameId(), question.getCategory(), question.getQuestion(), question.getAnswer(), question.getQuestionId());
    }

    // Delete a question
    public int deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE question_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
