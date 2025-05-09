package com.shurdev.androidurfu.front01;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;

public class MainActivity extends AppCompatActivity {

    int[] integers = null;
    int clicks = 0;
    ProgressBar indicatorBar;
    TextView statusView;
    TextView clicksView;
    Button progressBtn;
    Button clicksBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integers = new int[100];
        for (int i = 0; i < 100; i++) {
            integers[i] = i + 1;
        }
        indicatorBar = (ProgressBar) findViewById(R.id.indicator);
        statusView = findViewById(R.id.statusView);
        progressBtn = findViewById(R.id.progressBtn);
        progressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ProgressTask().execute();
            }
        });

        clicksView = findViewById(R.id.clicksView);
        clicksBtn = findViewById(R.id.clicksBtn);
        clicksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicks++;
                clicksView.setText("Clicks: " + clicks);
            }
        });
    }

    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            for (int i = 0; i < integers.length; i++) {

                publishProgress(i);
                SystemClock.sleep(400);
            }
            return (null);
        }

        @Override
        protected void onProgressUpdate(Integer... items) {
            indicatorBar.setProgress(items[0] + 1);
            statusView.setText("Статус: " + String.valueOf(items[0] + 1));
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getApplicationContext(), "Задача завершена", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}