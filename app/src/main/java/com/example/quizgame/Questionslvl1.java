package com.example.quizgame;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;



public class Questionslvl1 extends AppCompatActivity {
    private MediaPlayer fail, clapping,startbtn;
    private TextView tvQues, tvQuestionNo, tvScore,tvTimer;
    private Button nextbtn, prevbtn;
    private RadioGroup radioGroup;
    private RadioButton b1, b2, b3;

    int totalQues;
    int qQunter = 0;

    ColorStateList dfbcolor;

    CountDownTimer countDownTimer;

    boolean answered;
    int score;

    private Questions currentQues;


    private List<Questions> questionsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionslvl1);
        getWindow().setStatusBarColor(ContextCompat.getColor(Questionslvl1.this,R.color.purplelvl1));
        questionsList = new ArrayList<>();
        tvQues = findViewById(R.id.textQues);
        tvQuestionNo = findViewById(R.id.textQuesNo);
        tvScore = findViewById(R.id.textScore);
        tvTimer=findViewById(R.id.timer1);




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
                        countDownTimer.cancel();
                    } else {
                        Toast.makeText(Questionslvl1.this, "Please select your answer first", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQues();
                }
            }
        });


    }


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
                break;
        }
        if (qQunter < totalQues) {
            nextbtn.setText("Next");
        } else {
            nextbtn.setText("Finish");

        }

    }


    private void timer() {
        countDownTimer =new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText("00:" + l/1000);
            }

            @Override
            public void onFinish() {
                fail.start();
                showNextQues();


            }
        }.start();
    }

    private void addQuestions() {
        questionsList.add(new Questions("What is the result of? \n2 - 2", "1", "0", "4", 2));
        questionsList.add(new Questions("What is the result of? \n2 * 4", "10", "8", "9", 2));
        questionsList.add(new Questions("What is the result of? \n6 / 2", "3", "2", "6", 1));
        questionsList.add(new Questions("What is the result of? \n1 * 9", "1", "9", "5", 2));
        questionsList.add(new Questions("What is the result of? \n8 - 2", "2", "6", "8", 2));
        questionsList.add(new Questions("What is the result of? \n2 * 6", "4", "3", "12", 3));
        questionsList.add(new Questions("What is the result of? \n9 / 3", "6", "9", "3", 3));
        questionsList.add(new Questions("What is the result of? \n6 / 1", "6", "11", "5", 1));
        questionsList.add(new Questions("What is the result of? \n2 - 1", "1", "0", "2", 1));
        questionsList.add(new Questions("What is the result of? \n6 + 5", "5", "11", "8", 2));
    }
    private void showNextQues() {
        radioGroup.clearCheck();
        b1.setTextColor(dfbcolor);
        b2.setTextColor(dfbcolor);
        b3.setTextColor(dfbcolor);
        if (qQunter < totalQues) {
            timer();
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
            countDownTimer.cancel();
            Intent i = new Intent(Questionslvl1.this, Congratulation.class);
            startActivity(i);
        }
    }
    public void backlvl(View view) {
        startbtn.start();
        countDownTimer.cancel();
        Intent i = new Intent(Questionslvl1.this, MainActivity2.class);
        startActivity(i);
    }


}