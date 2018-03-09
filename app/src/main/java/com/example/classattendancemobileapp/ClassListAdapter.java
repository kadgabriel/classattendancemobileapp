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
 * @File:  ClassListAdapter.java
 * @Creation Date: 25/02/18
 * @Version: 1.0
 */

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
     List<ClassListItem> classItems; // the class data that the adapter object translates into UI objects
     Context context; // the application's current running environment

     /**
      * ClassListAdapter() <25/02/2018>
      * - the class constructor
      * @param: attendanceItems - data to put inside the views, context - the application's current running environment
      * @requires: none
      * @returns: a new ClassListAdapter instance
      */
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
