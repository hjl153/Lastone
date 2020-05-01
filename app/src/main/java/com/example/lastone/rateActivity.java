package com.example.lastone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class rateActivity extends AppCompatActivity implements Runnable {
     EditText input;
     TextView showout;
     float num;
    float a=0.1f,b=0.2f,c=0.3f;
    String TAG,updateDate="";
    Handler handler;
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
        updateDate=sp.getString("update_rate","");
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd");
        final String today_sdr=sdf.format(today);
        Log.i(TAG,"oncreate:更新时间"+updateDate);
        Log.i(TAG,"oncreate:当前时间"+today_sdr);
        if(!today_sdr.equals(updateDate)){
            Thread t=new Thread(this);
            t.start();

            Log.i(TAG,"oncreate:需要更新");
        }
        else{
            Log.i(TAG,"oncreate:不需要更新");
        }
                handler=new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if(msg.what==5){
                            Bundle bdl=(Bundle)msg.obj;
                            a=bdl.getFloat("dollar_rate");
                            b=bdl.getFloat("euro_rate");
                            c=bdl.getFloat("won_rate");
                            Log.i(TAG,"dollar_rate"+a);
                            Log.i(TAG,"euro_rate"+b);
                            Log.i(TAG,"won_rate"+c);
                            SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putFloat("dollar.rate",a);
                            editor.putFloat("euro.rate",b);
                            editor.putFloat("won.rate",c);
                            editor.putString("update_rate",today_sdr);
                            editor.commit();
                            Toast.makeText(rateActivity.this,"汇率已更新",Toast.LENGTH_LONG).show();;
                        }
                        super.handleMessage(msg);
                    }


                };


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
        else if(item.getItemId()==R.id.list_rate){
            Intent config = new Intent(this, RateListActivity.class);
            startActivity(config);
        }
        else if(item.getItemId()==R.id.mylist){
            Intent config = new Intent(this,List2Activity.class);
            startActivity(config);
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

    @Override
    public void run() {
        Log.i(TAG,"run()");
        for(int i=0;i<2;i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Bundle bundle = getFormatboc();
        Message msg=handler.obtainMessage(5);
        msg.obj=bundle;
        handler.sendMessage(msg);

    }
    private Bundle getFormatusdcny() {
        Bundle bundle=new Bundle();
        String url="http://www.usd-cny.com/bankofchina.htm";
        try {
            Document doc= Jsoup.connect(url).get();
            Log.i(TAG,"run="+doc.title());
            Elements tables=doc.getElementsByTag("table");
            Element table6=tables.get(0);
            Log.i(TAG,"run"+table6);
            Elements tds=table6.getElementsByTag("td");
            for(int i=0;i< tds.size();i+=6){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                Log.i(TAG,"run"+td1.text()+td2.text());
                String val=td1.text();
                String com=td2.text();
                if("美元".equals(val)){
                    bundle.putFloat("dollar_rate",100f/Float.parseFloat(com));
                }
                else if("欧元".equals(val)){
                    bundle.putFloat("euro_rate",100f/Float.parseFloat(com));
                }
                if("韩元".equals(val)){
                    bundle.putFloat("won_rate",100f/Float.parseFloat(com));
                }
            } }catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }
    private Bundle getFormatboc() {
        Bundle bundle=new Bundle();
        String url="https://www.boc.cn/sourcedb/whpj/";
        try {
            Document doc= Jsoup.connect(url).get();
            Log.i(TAG,"run="+doc.title());
            Elements tables=doc.getElementsByTag("table");
           Element table6=tables.get(1);
            Elements tds=table6.getElementsByTag("td");
            for(int i=0;i< tds.size();i+=8){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String val=td1.text();
                String com=td2.text();
                if("美元".equals(val)){
                     bundle.putFloat("dollar_rate",100f/Float.parseFloat(com));
            }
                else if("欧元".equals(val)){
                    bundle.putFloat("euro_rate",100f/Float.parseFloat(com));
                }
                if("韩国元".equals(val)){
                    bundle.putFloat("won_rate",100f/Float.parseFloat(com));
                }
        } }catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }

    public String changeString(InputStream in) throws IOException{
        final int buf=1024;
        final char[] buffer=new char[buf];
        final StringBuilder out=new StringBuilder();
        Reader inputStream=new InputStreamReader(in,"gb2312");
        while(true){
            int res=inputStream.read(buffer,0,buffer.length);
            if(res<0){
                break;
            }
            out.append(buffer,0,res);
        }

    return out.toString();
    }

}
