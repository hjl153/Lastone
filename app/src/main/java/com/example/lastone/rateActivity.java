package com.example.lastone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class rateActivity extends AppCompatActivity {
     EditText input;
     TextView showout;
     float num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        input=findViewById(R.id.rmb);
        showout=findViewById(R.id.showout);
    }
    @SuppressLint("DefaultLocale")
    public void onClick(View btn){
        String s1=input.getText().toString();
        if(s1.length()>0){
        num=Float.parseFloat(s1);}
        else{
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }
        float num2=0;
        if(btn.getId()==R.id.dollar){
            num2=num*(1/6.7f);
        }
       else if(btn.getId()==R.id.euro){
            num2=num*(1/11f);
        }
       else if(btn.getId()==R.id.won){
            num2=num*500f;
        }

       else{
            Intent hello=new Intent(this,pointActivity.class);
            startActivity(hello);
        }
        showout.setText(String.format("%.2f",num2));
    }
}