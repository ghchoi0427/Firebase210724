package com.example.firebase210724;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBoard;
    Button btnSchedule;
    Button btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBoard = findViewById(R.id.btn_board);
        btnSchedule = findViewById(R.id.btn_schedule);
        btnWeb = findViewById(R.id.btn_web);

        btnBoard.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BoardActivity.class)));
        btnSchedule.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ScheduleActivity.class)));
        btnWeb.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WebActivity.class)));
    }
}