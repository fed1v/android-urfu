package com.shurdev.androidurfu.task01parallelism;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shurdev.androidurfu.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))");
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31);");

        insertUser(db);

        TextView textView = findViewById(R.id.textView);
        textView.setText("");

        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        while (query.moveToNext()) {
            String name = query.getString(0);
            int age = query.getInt(1);
            textView.append("Name: " + name + " Age: " + age + "\n");
        }

        query.close();
        db.close();
    }

    private void insertUser(SQLiteDatabase db) {

        Cursor query = db.rawQuery("SELECT * FROM users", null);

        int usersCount = query.getCount();

        String userName = "User" + usersCount;

        ContentValues values = new ContentValues();
        values.put("name", userName);
        values.put("age", usersCount);

        db.insert("users", null, values);

        query.close();
    }
}