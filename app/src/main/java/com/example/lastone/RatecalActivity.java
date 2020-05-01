package com.example.lastone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RatecalActivity extends AppCompatActivity {
    Float rate=0f;
    EditText init;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratecal);
        String title=getIntent().getStringExtra("title");
        rate=getIntent().getFloatExtra("detail",0f);
        ((TextView)findViewById(R.id.title1)).setText(title);
        init=findViewById(R.id.init2);
        init.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView show=RatecalActivity.this.findViewById(R.id.show2);
                if(s.length()>0){
                    float val=Float.parseFloat(s.toString());
                    show.setText(val+"RMB==>"+(100/rate*val));
                }
                else{
                    show.setText("");
                }

            }
        });
    }
}
