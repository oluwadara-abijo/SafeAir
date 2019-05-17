package com.example.safeair.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.safeair.R;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        Button button = findViewById(R.id.retry_button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(NoInternetActivity.this, SplashActivity.class);
            startActivity(intent);
        });
    }
}
