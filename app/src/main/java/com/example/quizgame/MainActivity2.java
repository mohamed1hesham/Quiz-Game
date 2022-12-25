package com.example.quizgame;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
public class MainActivity2 extends AppCompatActivity {
    private MediaPlayer mediaplayer,startbtn;
    ImageButton b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startbtn =startbtn.create(this,R.raw.startbtnsound);
        b1=findViewById(R.id.lvl0btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startbtn.start();
                Intent i = new Intent(MainActivity2.this, Questionslvl0.class);
                startActivity(i); }});
        b2=findViewById(R.id.lvl1btn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startbtn.start();
                Intent i = new Intent(MainActivity2.this, Questionslvl1.class);
                startActivity(i); }});
        b3=findViewById(R.id.lvl2btn);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startbtn.start();
                Intent i = new Intent(MainActivity2.this, Questionlvl2.class);
                startActivity(i);
            }}); }
    public void backarrow (View view){
        startbtn.start();
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(i);
    }
}