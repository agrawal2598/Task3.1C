package com.task_3_1c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.task_3_1c.model.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    List<QuizModel> listOfQuestions;
    String selectedAnswer;

    AppCompatTextView tvShowUserName;
    AppCompatTextView tvProgressStatus;
    AppCompatTextView tvQuestionTitle;
    AppCompatTextView tvQuestion;
    RadioButton radioOption1;
    RadioButton radioOption2;
    RadioButton radioOption3;
    ProgressBar progressBar;

    AppCompatButton btnSubmit;
    AppCompatButton btnNext;
    int currentQuestion = 0;
    int totalQuestions = 0;
    RadioGroup radioGroup;
    int totalScore = 0;
    int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initViews();
        tvShowUserName.setText("Welcome " + getIntent().getExtras().getString("Name"));

        resetQuizData();

        btnSubmit.setOnClickListener(v -> {
            if (listOfQuestions.get(currentQuestion).getCorrectAnswer().equals(selectedAnswer)) {

                changeBgColorSelectedAnswer(selectedAnswer);
                totalScore++;
            } else {
                changeBgColorSelectedAnswer(selectedAnswer);
                changeBgColorWrongAnswer(listOfQuestions.get(currentQuestion).getCorrectAnswer());
            }

            btnSubmit.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
            currentQuestion++;
        });

        btnNext.setOnClickListener(v -> {
            if (currentQuestion < listOfQuestions.size())
                resetQuizData();
            else {
                finish();
                Intent intent = new Intent(getApplicationContext(), ShowResultActivity.class);
                intent.putExtra("Name", getIntent().getExtras().getString("Name"));
                intent.putExtra("Score", totalScore);
                startActivity(intent);
            }

        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.radioOption1:
                        selectedAnswer = "option1";
                        break;

                    case R.id.radioOption2:
                        selectedAnswer = "option2";
                        break;

                    case R.id.radioOption3:
                        selectedAnswer = "option3";
                        break;
                }
            }
        });
    }


    void initViews() {
        tvShowUserName = findViewById(R.id.tvShowUserName);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionTitle = findViewById(R.id.tvQuestionTitle);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progress);
        tvProgressStatus = findViewById(R.id.tvProgressStatus);

        radioOption1 = findViewById(R.id.radioOption1);
        radioOption2 = findViewById(R.id.radioOption2);
        radioOption3 = findViewById(R.id.radioOption3);
        radioGroup = findViewById(R.id.radioGroup);

        initQuizData();
    }

    void initQuizData() {

        listOfQuestions = new ArrayList<>();
        List<String> options1 = new ArrayList<>();
        options1.add("20");
        options1.add("12");
        options1.add("100");
        QuizModel model1 = new QuizModel(1, "Find '?'", "1, 1, 2, 2, 4, 8, ?", options1, "option2");
        listOfQuestions.add(model1);

        List<String> options2 = new ArrayList<>();
        options2.add("49");
        options2.add("31");
        options2.add("28");
        QuizModel model2 = new QuizModel(2, "Complete the series", "4, 9, 16, 25, 36, ?", options2, "option1");
        listOfQuestions.add(model2);


        List<String> options3 = new ArrayList<>();
        options3.add("H");
        options3.add("U");
        options3.add("Y");
        QuizModel model3 = new QuizModel(3, "Find the missing character '?'", "A,E,I,O,?", options3, "option2");
        listOfQuestions.add(model3);


        List<String> options4 = new ArrayList<>();
        options4.add("132");
        options4.add("12100");
        options4.add("112");
        QuizModel model4 = new QuizModel(4, "Find the missing number ?", "2, 10, 12, 120, ?", options4, "option1");
        listOfQuestions.add(model4);


        List<String> options5 = new ArrayList<>();
        options5.add("2");
        options5.add("3");
        options5.add("4");
        QuizModel model5 = new QuizModel(5, "Calculate sum ", "1+1=?", options5, "option1");
        listOfQuestions.add(model5);

        totalQuestions = listOfQuestions.size();
    }

    void changeBgColorSelectedAnswer(String option) {
        switch (option) {
            case "option1":
                radioOption1.setBackgroundResource(R.drawable.button_bg_correct_ans);
                break;

            case "option2":
                radioOption2.setBackgroundResource(R.drawable.button_bg_correct_ans);
                break;

            case "option3":
                radioOption3.setBackgroundResource(R.drawable.button_bg_correct_ans);
                break;
        }
    }

    void changeBgColorWrongAnswer(String option) {
        switch (option) {
            case "option1":
                radioOption1.setBackgroundResource(R.drawable.button_bg_wrong_ans);
                break;

            case "option2":
                radioOption2.setBackgroundResource(R.drawable.button_bg_wrong_ans);
                break;

            case "option3":
                radioOption3.setBackgroundResource(R.drawable.button_bg_wrong_ans);
                break;
        }
    }

    void resetQuizData() {
        tvQuestion.setText(listOfQuestions.get(currentQuestion).getQuestion());
        radioOption1.setText(listOfQuestions.get(currentQuestion).getOptionList().get(0));
        radioOption2.setText(listOfQuestions.get(currentQuestion).getOptionList().get(1));
        radioOption3.setText(listOfQuestions.get(currentQuestion).getOptionList().get(2));
        tvQuestionTitle.setText(listOfQuestions.get(currentQuestion).getQuestionTitle());
        btnSubmit.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.GONE);

        radioOption1.setBackgroundResource(R.drawable.selector_options_button);
        radioOption2.setBackgroundResource(R.drawable.selector_options_button);
        radioOption3.setBackgroundResource(R.drawable.selector_options_button);

        radioGroup.clearCheck();

        progressStatus = (currentQuestion * 100) / totalQuestions;
        progressBar.setProgress(progressStatus);

        tvProgressStatus.setText(currentQuestion + 1 + "/" + totalQuestions);
    }

}