/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): John Oliver
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.1 <15/03/2018> - Arielle Gabriel
 *        - added
 *
 *   Version 1.0 <07/03/2018> - John Oliver
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  AttendanceListAdapter.java
 * @Creation Date: 07/03/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AttendanceListAdapter extends RecyclerView.Adapter<AttendanceListAdapter.ViewHolder> {
     List<AttendanceListItem> attendanceItems; // the attendance data that the adapter object translates into UI objects
     Context context; // the application's current running environment
     Boolean editable;
     /**
      * AttendanceListAdapter() <07/03/2018>
      * - the class constructor
      * @param: attendanceItems - data to put inside the views, context - the application's current running environment
      * @requires: none
      * @returns: a new AttendanceListAdapter instance
      */
     public AttendanceListAdapter(List<AttendanceListItem> attendanceItems, Context context) {
          this.attendanceItems = attendanceItems;
          this.context = context;
          editable = true;
     }

     /**
      * AttendanceListAdapter() <07/03/2018>
      * - the class constructor
      * @param: attendanceItems - data to put inside the views, context - the application's current running environment, editable -
      * @requires: none
      * @returns: a new AttendanceListAdapter instance
      */
     public AttendanceListAdapter(List<AttendanceListItem> attendanceItems, Context context, Boolean editable) {
          this.attendanceItems = attendanceItems;
          this.context = context;
          this.editable = editable;
     }


     /**
      * onCreateViewHolder() <07/03/2018>
      * - android function called when the View widget is generated
      * @param: parent - the view group to which the widget belongs to, viewType - int to identify the type of the view
      * @requires: none
      * @returns: a new AttendanceListAdapter.ViewHolder instance
      */
     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_list_item, parent, false);
          return new ViewHolder(view);
     }

     /**
      * onBindViewHolder() <07/03/2018>
      * - android function called when the View widget is generated; main function is to actually assign values to the widgets
      * @param: holder - a ViewHolder class which references the View widget being created, position - the int value of the widget's position
      * @requires: none
      * @returns: none
      */
     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {
          AttendanceListItem attendanceListItem = attendanceItems.get(position);
          holder.statusTv.setText(attendanceListItem.getEntry());
          holder.nameTv.setText(attendanceListItem.getName());
          holder.snoTv.setText(attendanceListItem.getSno());

          if(!editable){
               switch(holder.statusTv.getText().toString()) {
                    case "L":
                         holder.statusTv.setText("LATE");
                         holder.cardView.setBackgroundResource(R.color.lateBackground);
                         break;

                    case "A":
                         holder.statusTv.setText("ABSENT");
                         holder.cardView.setBackgroundResource(R.color.absentBackground);
                         break;

                    case "P":
                         holder.statusTv.setText("PRESENT");
                         holder.cardView.setBackgroundResource(R.color.presentBackground);
                         break;
               }
          }
          else{
               holder.statusTv.setText("PRESENT");
               holder.cardView.setBackgroundResource(R.color.presentBackground);
          }
     }

     /**
      * getItemCount() <07/03/2018>
      * - gets the count of the items that the adapter processes
      * @param: none
      * @requires: none
      * @returns: int
      */
     @Override
     public int getItemCount() {
          return attendanceItems.size();
     }

     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
          public TextView nameTv;
          public TextView snoTv;
          public TextView statusTv;
          public CardView cardView;

          /**
           * ViewHolder() <07/03/2018>
           * - the class constructor
           * @param: itemView - reference to the View widget being created
           * @requires: none
           * @returns: a new AttendanceListAdapter.ViewHolder instance
           */
          public ViewHolder(View itemView){
               super(itemView);
               nameTv = itemView.findViewById(R.id.nameTv);
               snoTv = itemView.findViewById(R.id.descTv);
               statusTv = itemView.findViewById(R.id.statusTv);
               cardView = itemView.findViewById(R.id.cardView);
               cardView.setBackgroundResource(R.color.presentBackground);

               itemView.setOnClickListener(this);


          }

          /**
           * onClick() <07/03/2018>
           * - android function called when a widget is clicked
           * @param: view - reference to the view that was clicked
           * @requires: none
           * @returns: none
           */
          @Override
          public void onClick(View view) {
               if(editable){
                    switch (statusTv.getText().toString().substring(0, 1)) {
                         case "P":
                              statusTv.setText("LATE");
                              cardView.setBackgroundResource(R.color.lateBackground);
                              break;

                         case "L":
                              statusTv.setText("ABSENT");
                              cardView.setBackgroundResource(R.color.absentBackground);
                              break;

                         case "A":
                              statusTv.setText("PRESENT");
                              cardView.setBackgroundResource(R.color.presentBackground);
                              break;
                    }
               }

          }
     }
}
