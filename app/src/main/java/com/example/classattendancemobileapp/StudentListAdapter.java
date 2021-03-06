/**
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @File Author(s): John Oliver, Arielle Gabriel
*
* */

/**
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
*    Version 1.1 <22/02/2018> - Arielle Gabriel
*         - added lines to display present, late, absent columns
*
*    Version 1.0 <21/02/2018> - John Oliver
*         - created initial file for student list adapter
*
 */

/**
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  StudentListAdapter.java
* @Creation Date: 25/02/18
* @Version: 1.1
*
* */

package com.example.classattendancemobileapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
     List<StudentListItem> studentItems; // the student data that the adapter object translates into UI objects
     Context context; // the application's current running environment

     /**
      * StudentListAdapter() <25/02/2018>
      * - the class constructor
      * @param: studentItems - data to put inside the views, context - the application's current running environment
      * @requires: none
      * @returns: a new StudentListAdapter instance
      */
     public StudentListAdapter(List<StudentListItem> studentItems, Context context) {
          this.studentItems = studentItems; // list of "student items" to be put in the view
          this.context = context; // application's context
     }

     /**
      * onCreateViewHolder() <25/02/2018>
      * - android function called when the View widget is generated
      * @param: parent - the view group to which the widget belongs to, viewType - int to identify the type of the view
      * @requires: none
      * @returns: a new StudentListAdapter.ViewHolder instance
      */
     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.student_list_item, parent, false);
          return new ViewHolder(view);
     }

     /**
      * onBindViewHolder() <25/02/2018>
      * - android function called when the View widget is generated; main function is to actually assign values to the widgets
      * @param: holder - a ViewHolder class which references the View widget being created, position - the int value of the widget's position
      * @requires: none
      * @returns: none
      */
     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {
          StudentListItem studentListItem = studentItems.get(position);
          holder.nameTv.setText(studentListItem.getName());
          holder.snoTv.setText(studentListItem.getSno());
          holder.presentTv.setText(Integer.toString(studentListItem.getPresent()));
          holder.lateTv.setText(Integer.toString(studentListItem.getLate()));
          holder.absentTv.setText(Integer.toString(studentListItem.getAbsent()));
     }

     /**
      * getItemCount() <25/02/2018>
      * - gets the count of the items that the adapter processes
      * @param: none
      * @requires: none
      * @returns: int
      */
     @Override
     public int getItemCount() {
          return studentItems.size();
     }

     public class ViewHolder extends RecyclerView.ViewHolder{
          public TextView nameTv; // variable holder for student's name text view
          public TextView snoTv; // variable holder for student's student number text view
          public TextView presentTv; // variable holder for student's number of present entries text view
          public TextView lateTv; // variable holder for student's number of late entries text view
          public TextView absentTv; // variable holder for student's number of absent entries text view

          /**
           * ViewHolder() <25/02/2018>
           * - the class constructor
           * @param: itemView - reference to the View widget being created
           * @requires: none
           * @returns: a new StudentListAdapter.ViewHolder instance
           */
          public ViewHolder(View itemView) {
               super(itemView);

               nameTv = itemView.findViewById(R.id.nameTv);
               snoTv = itemView.findViewById(R.id.descTv);
               presentTv = itemView.findViewById(R.id.presentTv);
               lateTv = itemView.findViewById(R.id.lateTv);
               absentTv = itemView.findViewById(R.id.absentTv);
          }
     }
}
