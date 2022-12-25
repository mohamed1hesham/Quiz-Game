package com.example.quizgame;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class Questionslvl0 extends AppCompatActivity {
    private MediaPlayer fail, clapping,startbtn;
    private TextView tvQues, tvQuestionNo, tvScore;
    private Button nextbtn, prevbtn;
    private RadioGroup radioGroup;
    private RadioButton b1, b2, b3;
    int totalQues;
    int qQunter = 0;
    ColorStateList dfbcolor;
    boolean answered;
    int score;
    private Questions currentQues;
    private List<Questions> questionsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionslvl0);
        getWindow().setStatusBarColor(ContextCompat.getColor(Questionslvl0.this, R.color.Orangelvl0));
        questionsList = new ArrayList<>();
        tvQues = findViewById(R.id.textQues);
        tvQuestionNo = findViewById(R.id.textQuesNo);
        tvScore = findViewById(R.id.textScore);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        startbtn = startbtn.create(this, R.raw.startbtnsound);
        clapping = clapping.create(this, R.raw.clapping1);
        fail = fail.create(this, R.raw.fail);
        radioGroup = findViewById(R.id.radioGroup);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        nextbtn = findViewById(R.id.nextbtn);
        dfbcolor = b1.getTextColors();
        addQuestions();
        totalQues = questionsList.size();
        showNextQues();
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answered == false) {
                    if (b1.isChecked() || b2.isChecked() || b3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(Questionslvl0.this, "Please select your answer first", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQues(); } }}); }
    private void checkAnswer() {
        answered = true;
        RadioButton rbselected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbselected) + 1;
        if (answerNo == currentQues.getCorrectans()) {
            score += 100;
            tvScore.setText("Score : " + score);
            clapping.start();
        } else {
            fail.start();
        }
        b1.setTextColor(Color.RED);
        b2.setTextColor(Color.RED);
        b3.setTextColor(Color.RED);
        switch (currentQues.getCorrectans()) {
            case 1:
                b1.setTextColor(Color.GREEN);
                break;
            case 2:
                b2.setTextColor(Color.GREEN);
                break;
            case 3:
                b3.setTextColor(Color.GREEN);
                break; }
        if (qQunter < totalQues) {
            nextbtn.setText("Next");
        } else {
            nextbtn.setText("Finish"); } }
    private void showNextQues() {
        radioGroup.clearCheck();
        b1.setTextColor(dfbcolor);
        b2.setTextColor(dfbcolor);
        b3.setTextColor(dfbcolor);
        if (qQunter < totalQues) {
            currentQues = questionsList.get(qQunter);
            tvQues.setText(currentQues.getQuestion());
            b1.setText(currentQues.getAnswer1());
            b2.setText(currentQues.getAnswer2());
            b3.setText(currentQues.getAnswer3());
            qQunter++;
            nextbtn.setText("Submit");
            tvQuestionNo.setText("Question: " + qQunter + " / " + totalQues);
            answered = false;
        } else {
            Intent i = new Intent(Questionslvl0.this, Congratulation.class);
            startActivity(i); } }
    private void addQuestions() {
        questionsList.add(new Questions("What is the result of? \n2 + 2", "3", "8", "4", 3));
        questionsList.add(new Questions("What is the result of? \n2 * 4", "8", "10", "12", 1));
        questionsList.add(new Questions("What is the result of? \n6 / 2", "1", "3", "6", 2));
        questionsList.add(new Questions("What is the result of? \n5 + 7", "12", "6", "5", 1));
        questionsList.add(new Questions("What is the result of? \n2 + 8", "2", "10", "8", 2));
        questionsList.add(new Questions("What is the result of? \n8 / 4", "4", "3", "2", 3));
        questionsList.add(new Questions("What is the result of? \n6 / 0", "8", "10", "Not Valid", 3));
        questionsList.add(new Questions("What is the result of? \n6 * 1", "6", "11", "5", 1));
        questionsList.add(new Questions("What is the result of? \n4 - 4", "0", "4", "8", 1));
        questionsList.add(new Questions("What is the result of? \n2 + 5", "3", "7", "8", 2));
    }
    public void backlvl(View view) {
        startbtn.start();
        Intent i = new Intent(Questionslvl0.this, MainActivity2.class);
        startActivity(i); }}