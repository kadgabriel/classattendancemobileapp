package com.example.classattendancemobileapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oli on 2/25/2018.
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
     List<StudentListItem> studentItems;
     Context context;

     public StudentListAdapter(List<StudentListItem> studentItems, Context context) {
          this.studentItems = studentItems;
          this.context = context;
     }

     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.student_list_item, parent, false);
          return new ViewHolder(view);
     }

     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {
          StudentListItem studentListItem = studentItems.get(position);
          holder.nameTv.setText(studentListItem.getName());
          holder.snoTv.setText(studentListItem.getSno());
     }

     @Override
     public int getItemCount() {
          return studentItems.size();
     }

     public class ViewHolder extends RecyclerView.ViewHolder{
          public TextView nameTv;
          public TextView snoTv;

          public ViewHolder(View itemView) {
               super(itemView);

               nameTv = itemView.findViewById(R.id.nameTv);
               snoTv = itemView.findViewById(R.id.descTv);
          }
     }
}
