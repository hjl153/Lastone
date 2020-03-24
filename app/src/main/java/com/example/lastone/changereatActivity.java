package com.example.lastone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class changereatActivity extends AppCompatActivity {
    EditText dollar1,euro1,won1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changereat);
        dollar1=findViewById(R.id.chdollar);
        euro1=findViewById(R.id.cheuro);
        won1=findViewById(R.id.chwon);
    }
    public void onchange(View btn){
        String dollar=dollar1.getText().toString();
        float dollarm=Float.parseFloat(dollar);
        String euro=euro1.getText().toString();
        float eurom=Float.parseFloat(euro);
        String won=won1.getText().toString();
        float wonm=Float.parseFloat(won);
        Intent intent = new Intent();
        intent.putExtra("ax",dollarm);
        intent.putExtra("bx",eurom);
        intent.putExtra("cx",wonm);
        intent.setClass(changereatActivity.this, rateActivity.class);
        startActivity(intent);
    }
}
