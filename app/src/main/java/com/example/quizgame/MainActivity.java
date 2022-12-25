package com.example.quizgame;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaplayer,startbtn;
    Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbtn =startbtn.create(this,R.raw.startbtnsound);
        b1 = findViewById(R.id.startBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startbtn.start();
                Intent i=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);


            }
        });
        mediaplayer =mediaplayer.create(MainActivity.this,R.raw.backgroundmusic);
        mediaplayer.setLooping(true);
        mediaplayer.start();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaplayer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaplayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaplayer.stop();
        mediaplayer.release();
    }
}