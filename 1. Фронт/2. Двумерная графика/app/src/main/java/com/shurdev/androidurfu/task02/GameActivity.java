package com.shurdev.androidurfu.task02;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;


public class GameActivity extends AppCompatActivity {

    GameField gameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameField = findViewById(R.id.gameField);
        Button buttonBack = findViewById(R.id.btnBack);
        TextView tvScore = findViewById(R.id.tvScore);
        new CountDownTimer(Integer.MAX_VALUE, 200) {
            @Override
            public void onTick(long l) {
                tvScore.setText(gameField.getScore() + "");
            }

            @Override
            public void onFinish() {

            }
        }.start();
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameField.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameField.resume();
    }
}