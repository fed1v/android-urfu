package com.shurdev.androidurfu.front01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            onButtonClicked(textView);
        });
    }

    private void onButtonClicked(TextView textView) {
        Runnable runnable = () -> {
            Calendar c = Calendar.getInstance();

            int hours = c.get(Calendar.HOUR_OF_DAY);
            int minutes = c.get(Calendar.MINUTE);
            int seconds = c.get(Calendar.SECOND);

            String time = hours + ":" + minutes + ":" + seconds;

            textView.post(() -> textView.setText(time));
        };

        new Thread(runnable).start();
    }
}