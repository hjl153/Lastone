package com.example.lastone;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class List2Activity<onItemClick> extends ListActivity implements Runnable, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    Handler handler;
    private ArrayList<HashMap<String ,String>> listItems;
    private SimpleAdapter listItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        MyAdapter myAdapter=new MyAdapter(this,R.layout.activity_list2,listItems);
        this.setListAdapter(myAdapter);

        Thread t=new Thread(this);
        t.start();
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 5) {
                    List<HashMap<String,String>> retlist= (List<HashMap<String, String>>) msg.obj;

                    SimpleAdapter adapter  =new SimpleAdapter(List2Activity.this,retlist,R.layout.activity_list2,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail});
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);

            }

        };
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }
    private  void initListView(){
        listItems=new ArrayList<HashMap<String ,String>>();
        for(int i=0;i<10;i++){
            HashMap<String ,String> map=new HashMap<String ,String>();
            map.put("ItemTitle","Rate"+i);
            map.put("ItemDetail","detail"+i);
            listItems.add(map);
        }
        listItemAdapter =new SimpleAdapter(this,listItems,R.layout.activity_list2,
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail});
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      List<HashMap<String,String>> ratelist=new ArrayList<HashMap<String,String>>();
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
               HashMap<String,String> map=new HashMap<String,String>();
                map.put("ItemTitle",val);
                map.put("ItemDetail",com);
                ratelist.add(map);
            } }catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(5);
        msg.obj=ratelist;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String ,String> map= (HashMap<String, String>) getListView().getItemAtPosition(position);
        String titlestr=map.get("ItemTitle");
        String detailstr=map.get("ItemDetail");
        Log.i("TAG",titlestr+detailstr);
        TextView title = view.findViewById(R.id.itemTitle);
        TextView detail=view.findViewById(R.id.itemDetail);
        String title2=String.valueOf(title.getText());
        String detail2=String.valueOf(detail.getText());
        detail.setText("detail"+map.get("ItemDetail"));
        Log.i("TAG",title2+detail2);

        Intent ratecal=new Intent(this,RatecalActivity.class);
        ratecal.putExtra("title",titlestr);
        ratecal.putExtra("detail",Float.parseFloat(detailstr));
        startActivity(ratecal);

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Longclick","cilck");
        return true;
    }
}
