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


public class Questionlvl2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_questionlvl2);
        getWindow().setStatusBarColor(ContextCompat.getColor(Questionlvl2.this,R.color.greenlvl2));
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
                        Toast.makeText(Questionlvl2.this, "Please select your answer first", Toast.LENGTH_SHORT).show();
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
        countDownTimer =new CountDownTimer(10000,1000) {
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
        questionsList.add(new Questions("What is the result of? \n2 / 2", "3", "1", "4", 2));
        questionsList.add(new Questions("What is the result of? \n6 / 3", "2", "10", "12", 1));
        questionsList.add(new Questions("What is the result of? \n12 / 4", "1", "3", "6", 2));
        questionsList.add(new Questions("What is the result of? \n6 * 2", "12", "6", "5", 1));
        questionsList.add(new Questions("What is the result of? \n1 * 4", "3", "4", "11", 2));
        questionsList.add(new Questions("What is the result of? \n10 / 5", "4", "3", "2", 3));
        questionsList.add(new Questions("What is the result of? \n6 - 3", "4", "0", "3", 3));
        questionsList.add(new Questions("What is the result of? \n11 - 7", "4", "6", "3", 1));
        questionsList.add(new Questions("What is the result of? \n5 - 2", "3", "4", "8", 1));
        questionsList.add(new Questions("What is the result of? \n11 + 1", "10", "12", "11", 2));
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
            Intent i = new Intent(Questionlvl2.this, Congratulation.class);
            startActivity(i);

        }
    }
    public void backlvl(View view) {
        startbtn.start();
        countDownTimer.cancel();
        Intent i = new Intent(Questionlvl2.this, MainActivity2.class);
        startActivity(i);
    }

}