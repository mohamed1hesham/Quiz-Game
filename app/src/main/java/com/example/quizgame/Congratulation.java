package com.example.quizgame;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
public class Congratulation extends AppCompatActivity {
    MediaPlayer startbtn,clapping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        startbtn = startbtn.create(this, R.raw.startbtnsound);
        clapping = clapping.create(this, R.raw.clapping);
        getWindow().setStatusBarColor(ContextCompat.getColor(Congratulation.this, R.color.background));
        clapping.start();

    }
    public void gotolvlpage(View view){
        startbtn.start();
        Intent i = new Intent(Congratulation.this, MainActivity2.class);
        startActivity(i);
    }
}
