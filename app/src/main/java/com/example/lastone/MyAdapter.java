package com.example.lastone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {
    public static final String TAG="MyAdapter";
    public MyAdapter(@NonNull Context context, int resource, ArrayList<HashMap<String,String>> list) {
        super(context, resource,list);
    }
    public View getView(int position, View contentView, ViewGroup parent){
        View itemView=contentView;
        if(itemView==null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.activity_list2,parent,false);
        }
        Map<String,String> map= (Map<String, String>) getItem(position);
        TextView title=itemView.findViewById(R.id.itemTitle);
        TextView detail=itemView.findViewById(R.id.itemDetail);
        title.setText("Title"+map.get("ItemTitle"));
        detail.setText("detail"+map.get("ItemDetail"));
        return itemView;
    }

}
