package com.example.lastone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.DoubleBuffer;

public class week4Activity extends AppCompatActivity implements View.OnClickListener {
    TextView out;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week4);
        out=findViewById(R.id.tx_an);
        edit=findViewById(R.id.tx_out);
        Button btn=findViewById(R.id.bnt_ch);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("input","……");
        String oldwd=edit.getText().toString();
        double Oldwd= Double.parseDouble(oldwd);
        double newwd=Oldwd*1.8+32;
        out.setText("结果为："+newwd);
    }
}
