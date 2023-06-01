package com.example.firstaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {
    private TextView welcomeText;
    private static final int SPLASH_DURATION = 2000; // Duration in milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        welcomeText = findViewById(R.id.welcome);

        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay >= 6 && hourOfDay < 12) {
            welcomeText.setText("GOOD MORNING");
        } else if (hourOfDay >= 12 && hourOfDay < 18) {
            welcomeText.setText("GOOD AFTERNOON");
        } else {
            welcomeText.setText("GOOD EVENING");
        }
        // Handler to delay opening the next layout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity or the desired activity
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}