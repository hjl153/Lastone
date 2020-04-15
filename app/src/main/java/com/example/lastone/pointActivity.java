package com.example.lastone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class pointActivity extends AppCompatActivity {
    TextView score1,score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        score1=findViewById(R.id.score1);
        score2=findViewById(R.id.score2);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String scorea=score1.getText().toString();
        String scoreb=score2.getText().toString();
        Log.i("TAG","onSaveInstanceState");
        outState.putString("sc1",scorea);
        outState.putString("sc2",scoreb);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String scorea=savedInstanceState.getString("sc1");
        String scoreb=savedInstanceState.getString("sc2");
        Log.i("TAG","onRestoreInstanceState");
        score1.setText(scorea);
        score2.setText(scoreb);
    }

    public void button1(View sc1){
        if(sc1.getId()==R.id.sc1){
            showscore1(1);}
        if(sc1.getId()==R.id.sc4){
            showscore2(1);}
    }
    public void button2(View sc2){
        if(sc2.getId()==R.id.sc2){
            showscore1(2);}
        if(sc2.getId()==R.id.sc5){
            showscore2(2);}
    }
    public void button3(View sc3){
        if(sc3.getId()==R.id.sc3){
            showscore1(3);}
        if(sc3.getId()==R.id.sc6){
            showscore2(3);}
    }
    public void button4(View reset1){
        if(reset1.getId()==R.id.reset1){
            score1.setText("0");}
        if(reset1.getId()==R.id.reset2){
            score2.setText("0");}
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
