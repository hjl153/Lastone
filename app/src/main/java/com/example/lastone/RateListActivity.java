package com.example.lastone;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends ListActivity implements  Runnable{
    Handler handler;
    protected void onCreate(Bundle savedInstanceState){
        String data[]={"wait..."};
        super.onCreate(savedInstanceState);
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
        Thread t=new Thread(this);
        t.start();
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 5) {
                    List<String> retlist =( List<String>)msg.obj;

                    ListAdapter adapter = new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1, retlist);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);

            }

    };}


    @Override
    public void run(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        List<String> ratelist=new ArrayList<String>();
        String url="https://www.boc.cn/sourcedb/whpj/";
        try {
            Document doc= Jsoup.connect(url).get();
            Log.i("TAG","run="+doc.title());
            Elements tables=doc.getElementsByTag("table");
            Element table6=tables.get(1);
            Elements tds=table6.getElementsByTag("td");
            for(int i=0;i< tds.size();i+=8){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String val=td1.text();
                String com=td2.text();
               ratelist.add(val+"==>"+com);
                Log.i("TAG","run="+ratelist);
            } }catch (IOException e) {
            e.printStackTrace();
        }
            Message msg=handler.obtainMessage(5);
            msg.obj=ratelist;
            handler.sendMessage(msg);
    }
}
