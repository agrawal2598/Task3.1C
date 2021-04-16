package com.task_3_1c;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

public class ShowResultActivity extends AppCompatActivity {

    AppCompatButton btnTakeNewQuiz;
    AppCompatButton btnFinish;
    AppCompatTextView tvShowScore;
    AppCompatTextView tvName;
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);


        name=getIntent().getExtras().getString("Name");

        btnTakeNewQuiz = findViewById(R.id.btnTakeNewQuiz);
        btnFinish = findViewById(R.id.btnFinish);
        tvName = findViewById(R.id.tvName);
        tvShowScore = findViewById(R.id.tvScore);

        tvName.setText(getString(R.string.congrats)+" "+name);

        tvShowScore.setText(getIntent().getExtras().getInt("Score")+"/5");
        btnTakeNewQuiz.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(getApplicationContext(), QuizActivity.class).putExtra("Name",name));

        });

        btnFinish.setOnClickListener(v->{
            finish();
        });
    }

}