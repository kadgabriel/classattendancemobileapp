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
 *   Version 1.0 <08/02/2018> - John Oliver
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  ViewClassActivity.java
 * @Creation Date: 08/02/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ViewClassActivity extends AppCompatActivity {

     ListView studentListView; // variable holder for the ListView widget to display the list of students
     TextView noStudentTextView; // variable holder for the TextView widget to display the 'no students' notice

     /**
      * onCreate() <08/02/2018>
      * - android function called when the activity is created
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: none
      */
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.view_class_activity);
          Intent intent = getIntent();
          setTitle(intent.getStringExtra("CLASS_NAME"));

          studentListView = findViewById(R.id.studentListView);
          noStudentTextView = findViewById(R.id.noStudentTextView);
          String[] studentNames = new String[0];
          if(studentNames.length == 0)
               noStudentTextView.setVisibility(View.VISIBLE);
    }
}
