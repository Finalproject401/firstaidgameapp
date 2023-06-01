package com.example.firstaidapp;

public class Question {
    private int id;
    private int level;
    private String text;
    private boolean correctAnswer;

    // Constructor, getters, and setters
    public Question(int id, int level, String text, boolean correctAnswer) {
        this.id = id;
        this.level = level;
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
