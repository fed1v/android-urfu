package com.shurdev.androidurfu.task04algorithms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;


public class MainActivity extends AppCompatActivity {

    String[] countries = {"Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай", "Россия", "США",
            "Китай", "Германия", "Франция", "Эквадор", "Сербия", "Узбекистан", "Япония", "Южная Корея",
            "Северная Корея", "Казахстан", "Чехия", "Монголия", "Индия"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView countriesList = findViewById(R.id.countriesList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, countries
        );

        countriesList.setAdapter(adapter);
    }
}