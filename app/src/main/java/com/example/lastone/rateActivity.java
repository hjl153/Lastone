package com.example.lastone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class rateActivity extends AppCompatActivity {
     EditText input;
     TextView showout;
     float num;
    float a=0.1f,b=0.2f,c=0.3f;
    String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        input=findViewById(R.id.rmb);
        showout=findViewById(R.id.showout);
        SharedPreferences sp=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        a=sp.getFloat("dollar.rate",0.0f);
        b=sp.getFloat("euro.rate",0.0f);
        c=sp.getFloat("won.rate",0.0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menurate){
            openone();

        }
        return true;
    }

    private void openone() {
        Intent config = new Intent(this, changereatActivity.class);
        config.putExtra("key_dollar", a);
        config.putExtra("key_euro", b);
        config.putExtra("key_won", c);
        Log.i(TAG, "open=dollar" + a);
        Log.i(TAG, "open=euro" + b);
        Log.i(TAG, "open=won" + c);
        startActivityForResult(config, 1);
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
            num2=num*a;
        }
        else if(btn.getId()==R.id.euro){
            num2=num*b;
        }
        else if(btn.getId()==R.id.won){
            num2=num*c;
        }
        showout.setText(String.valueOf(num2));
        if(btn.getId()==R.id.openpage){
            openone();
        }
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1||requestCode==2){
            Bundle bundle=data.getExtras();
            a=bundle.getFloat("key_dollar1",0.1f);
            b=bundle.getFloat("key_euro1",0.1f);
            c=bundle.getFloat("key_won1",0.1f);
            Log.i(TAG,"onActivity=dollar"+a);
            Log.i(TAG,"onActivity=euro"+b);
            Log.i(TAG,"onActivity=won"+c);
            SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putFloat("dollar.rate",a);
            editor.putFloat("euro.rate",b);
            editor.putFloat("won.rate",c);
            editor.commit();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
