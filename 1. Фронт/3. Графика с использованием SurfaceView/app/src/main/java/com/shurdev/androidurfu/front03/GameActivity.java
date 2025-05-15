package com.shurdev.androidurfu.front03;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;


public class GameActivity extends AppCompatActivity {

    GameSurfaceView gameSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameSurfaceView = findViewById(R.id.gameSurfaceView);
        Button buttonBack = findViewById(R.id.btnBack);
        TextView tvScore = findViewById(R.id.tvScore);
        new CountDownTimer(Integer.MAX_VALUE, 200) {
            @Override
            public void onTick(long l) {
                tvScore.setText(gameSurfaceView.getScore() + "");
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
        gameSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameSurfaceView.resume();
    }
}