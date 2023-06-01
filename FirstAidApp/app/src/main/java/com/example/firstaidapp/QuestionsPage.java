package com.example.firstaidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionsPage extends AppCompatActivity {
    private TextView timerText, timerLevel, lifeText;
    private TextView scoreText;
    private int score = 0;
    private int timesPlayed = 0;
    private int livesRemaining = 3;
    private CountDownTimer countDownTimer;
    private TextView questionText;
    private QuestionManager questionManager;

    private ArrayList<Question> questions;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);
        timesPlayed = loadTimesPlayed();

        timerText = findViewById(R.id.timerText);
        scoreText = findViewById(R.id.scoreText);
        timerLevel = findViewById(R.id.timerLevel);
        lifeText = findViewById(R.id.lifeText);

        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);
        Button homeButton = findViewById(R.id.homeButton);
        // Initialize the QuestionManager
        questionManager = new QuestionManager();

        // Get references to your TextViews in the layout
        questionText = findViewById(R.id.questionText);

        updateLifeText();

        // Display a random question initially
        displayRandomQuestion();
        //lifeText.setText(livesRemaining);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the selected answer is correct
                boolean isAnswerCorrect = true;
                if (isAnswerCorrect) {
                    // The selected answer is correct
                    // Increment the score, display another random question, and start the countdown timer
                    score++;
                    updateScoreText();
                    displayRandomQuestion();
                    countDownTimer.start();
                    Toast.makeText(QuestionsPage.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                } else {
                    // The selected answer is incorrect
                    // Decrease the livesRemaining count and check if the game is over
                    livesRemaining--;
                    if (livesRemaining == 0) {
                        // The game is over as all lives are lost
                        // Reset the score and livesRemaining, and display game over message
                        score = 0;
                        livesRemaining = 3;
                        updateScoreText();
                        displayGameOverMessage();
                    } else {

                        // The game is not over, display another random question
                        displayRandomQuestion();
                        countDownTimer.start();
                    }
                    Toast.makeText(QuestionsPage.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();

                }
            }


        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the selected answer is correct
                boolean isAnswerCorrect = true;
                if (isAnswerCorrect) {
                    // Increment the score, display another random question, and start the countdown timer
                    score++;
                    updateScoreText();
                    displayRandomQuestion();
                    countDownTimer.start();
                    Toast.makeText(QuestionsPage.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                } else {
                    // Decrease the livesRemaining count and check if the game is over
                    livesRemaining--;
                    if (livesRemaining == 0) {
                        // The game is over as all lives are lost
                        // Reset the score and livesRemaining, and display game over message
                        score = 0;
                        livesRemaining = 3;
                        updateScoreText();
                        displayGameOverMessage();
                    } else {
                        //display another random question
                        displayRandomQuestion();
                        countDownTimer.start();
                    }
                    Toast.makeText(QuestionsPage.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set the countdown duration in milliseconds (30 seconds = 30,000 milliseconds)
        long countdownDuration = 30000;
        countDownTimer = new CountDownTimer(countdownDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer text with the remaining time
                long seconds = millisUntilFinished / 1000;
                String timeLeftFormatted = String.format("Timer: %02d:%02d", seconds / 60, seconds % 60);
                timerText.setText(timeLeftFormatted);

            }

            @Override
            public void onFinish() {
                // Timer finished, perform any desired action
                timerText.setText("Timer: 00:00");
                displayRandomQuestion();
                countDownTimer.start();
            }
        };
        // Start the countdown timer
        countDownTimer.start();
    }

    private void updateLifeText() {
        lifeText.setText("Lives: " + livesRemaining);
    }

    private void displayGameOverMessage() {
        //Display the game over message when life goes below 1
    }

    private int loadTimesPlayed() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("timesPlayed", 0);
    }

    private boolean isAnswerCorrect(boolean selectedAnswer) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            boolean correctAnswer = currentQuestion.isCorrectAnswer();
            return selectedAnswer == correctAnswer;
        }
        return false;
    }


    private Question getCurrentQuestion() {
        if (questions != null && currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    private void displayRandomQuestion() {
        Question randomQuestion = questionManager.getRandomQuestion();
        if (randomQuestion != null) {
            questionText.setText(randomQuestion.getText());
        } else {
            questionText.setText("No questions available");
        }
    }

    private void updateScoreText() {
        // Update the score text with the current score
        scoreText.setText("Score: " + score);
        if (score == 10) {
            Toast.makeText(this, "Congratulations for finishing Level 1", Toast.LENGTH_SHORT).show();
            timerLevel.setText("Level 2");
        } else if (score == 20) {
            Toast.makeText(this, "Congratulations for finishing Level 2", Toast.LENGTH_SHORT).show();
            timerLevel.setText("Level 3");
        }
        saveScore(score);
    }

    @Override
    protected void onDestroy() {
        timesPlayed++;
        saveTimesPlayed(timesPlayed);

        super.onDestroy();
        // Cancel the countdown timer to avoid memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

    }

    private void saveTimesPlayed(int timesPlayed) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("timesPlayed", timesPlayed);
        editor.apply();
    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score", score);
        editor.apply();
    }


}