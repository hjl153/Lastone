package com.example.lastone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class changereatActivity extends AppCompatActivity {
    EditText dollar1,euro1,won1;
    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changereat);
        Intent intent = getIntent();
        Float dollar2=intent.getFloatExtra("key_dollar",0.1f);
        Float euro2=intent.getFloatExtra("key_euro",0.1f);
        Float won2=intent.getFloatExtra("key_won",0.1f);
        dollar1=findViewById(R.id.chdollar);
        euro1=findViewById(R.id.cheuro);
        won1=findViewById(R.id.chwon);
        dollar1.setText(String.valueOf(dollar2));
        euro1.setText(String.valueOf(euro2));
        won1.setText(String.valueOf(won2));
    }
    public void onchange(View btn){
        Log.i(TAG,"save:");
    }
}
