package com.example.firstaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    private TextView win, lose, score, times;
    private Button resetButton, backButton;
    private int sharedScore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        win = findViewById(R.id.win);
        lose = findViewById(R.id.lose);
        score = findViewById(R.id.score);
        times = findViewById(R.id.times);
        resetButton = findViewById(R.id.resetButton);
        backButton = findViewById(R.id.backButton);

        // Load the shared score
        sharedScore = loadScore();
        int timesPlayed = loadTimesPlayed();
        score.setText(String.valueOf(sharedScore));
        times.setText(String.valueOf(timesPlayed));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                win.setText("0");
                score.setText("0");
                lose.setText("0");
                times.setText("0");

                // Reset the shared score
                saveTimesPlayed(0);
                saveScore(0);
                sharedScore = 0;

            }

            private void saveTimesPlayed(int i) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("timesPlayed", timesPlayed);
                editor.apply();
            }
        });
    }

    private int loadTimesPlayed() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("timesPlayed", 0);
    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score", score);
        editor.apply();
    }

    private int loadScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("score", 0);
    }
    
}
