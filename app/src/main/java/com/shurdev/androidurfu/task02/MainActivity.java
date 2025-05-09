package com.shurdev.androidurfu.task02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;


public class MainActivity extends AppCompatActivity {

    private ImageButton startButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.btnStart);
        exitButton = findViewById(R.id.btnExit);

        startButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        exitButton.setOnClickListener(view -> {
            finish();
        });
    }
}