package com.example.lastone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class pointActivity extends AppCompatActivity {
    TextView score1,score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        score1=findViewById(R.id.score1);
        score2=findViewById(R.id.score2);
    }
    public void button1(View sc1){
        showscore1(1);
    }
    public void button2(View sc2){
        showscore1(2);
    }
    public void button3(View sc3){
        showscore1(3);
    }
    public void button4(View sc4){
        showscore2(1);
    }
    public void button5(View sc5){
        showscore2(2);
    }
    public void button6(View sc6){
        showscore2(3);
    }
    public void button7(View reset1){
         score1.setText("0");
    }
    public void button8(View reset2){
         score2.setText("0");
    }
    public void showscore1(int i1){
        Log.i("ok","ok1");
        String oldsc1=score1.getText().toString();
        int newsc1=Integer.parseInt(oldsc1)+i1;
        score1.setText(""+newsc1);
    }
    public void showscore2(int i2){
        Log.i("ok","ok1");
        String oldsc2=score2.getText().toString();
        int newsc2=Integer.parseInt(oldsc2)+i2;
        score2.setText(""+newsc2);
    }
}
