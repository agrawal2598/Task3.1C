package com.task_3_1c;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    AppCompatButton btnStartQuiz;
    AppCompatEditText etEnterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnStartQuiz = findViewById(R.id.btnStart);
        etEnterName = findViewById(R.id.etEnterName);

        btnStartQuiz.setOnClickListener(v -> {
            if (etEnterName.getText().toString().isEmpty())
                etEnterName.setError("Please Enter Your Name");
            else {
                finish();
                startActivity(new Intent(getApplicationContext(), QuizActivity.class).putExtra("Name",etEnterName.getText().toString()));
            }
        });

    }

}