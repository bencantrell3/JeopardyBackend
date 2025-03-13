package com.example.JeopardyBackend.model;

public class Question {
    private int gameId;
    private int questionId;
    private String category;
    private String question;
    private String answer;

    // Constructor
    public Question() {
        this.gameId = 0;
        this.questionId = 0;
        this.category = "";
        this.question = "";
        this.answer = "";
    }

    public Question(int gameId, int questionId, String category, String question, String answer) {
        this.gameId = gameId;
        this.questionId = questionId;
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString() {
        return "Question{" +
                "game_id=" + gameId +
                ", question_id=" + questionId +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
