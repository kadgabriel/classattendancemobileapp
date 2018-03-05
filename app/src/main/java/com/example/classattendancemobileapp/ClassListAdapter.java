package com.example.classattendancemobileapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ClassListAdapter extends BaseAdapter {
     List<ClassListItem> classItems;
     Context context;

     public ClassListAdapter(List<ClassListItem> classItems, Context context) {
          this.classItems = classItems;
          this.context = context;
     }

     @Override
     public int getCount() {
          return classItems.size();
     }

     @Override
     public ClassListItem getItem(int i) {
          return classItems.get(i);
     }

     @Override
     public long getItemId(int i) {
          return 0;
     }

     @Override
     public View getView(int i, View view, ViewGroup viewGroup) {
          ClassListItem classListItem = classItems.get(i);

          if(view == null){
               LayoutInflater layoutInflater = LayoutInflater.from(context);
               view = layoutInflater.inflate(R.layout.class_list_item, null);
          }

          TextView nameTv = view.findViewById(R.id.nameTv);
          TextView descTv = view.findViewById(R.id.descTv);

          nameTv.setText(classListItem.getName());
          descTv.setText(classListItem.getDesc());

          return view;
     }
}
