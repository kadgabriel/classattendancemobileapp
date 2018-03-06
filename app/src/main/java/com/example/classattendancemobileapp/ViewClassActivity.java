/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): John Oliver, Arielle Gabriel
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.0 <08/02/2018> - John Oliver
 *        - created initial file
 *
 *   Version 1.1 <22/02/2018> - John Oliver
 *        - used an instance of StudentController to finish the functional student ListView. Added code comments
 *
 *   Version 1.2 <23/02/2018> - John Oliver
 *        - fixed a bug
 *
 *   Version 1.3 <06/03/2018> - Arielle Gabriel
 *        - added attendance count
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
 * @Version: 1.3
 */

package com.example.classattendancemobileapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.classattendancemobileapp.database.Classes;
import com.example.classattendancemobileapp.database.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ViewClassActivity extends AppCompatActivity {

     final int[] customGradients = {R.drawable.custom_gradient_1, R.drawable.custom_gradient_2, R.drawable.custom_gradient_3, R.drawable.custom_gradient_4};

     String className;
     StudentController studentController; // the student controller object which is directly connected to the database
     ClassController classController;
     List<Student> studentList; // the list of students returned by the controller
     List<StudentListItem> studentListItems;
     TextView noStudentTv;
     TextView classNameTv;
     TextView classDescTv;
     Button addStudentsButton;
     CollapsingToolbarLayout collapsingToolbarLayout;
     RecyclerView studentsRv;
     RecyclerView.Adapter adapter;

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
          setContentView(R.layout.activity_view_class);
          getSupportActionBar().hide();
          Intent intent = getIntent();

          className = intent.getStringExtra("CLASS_NAME");
          collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
          studentsRv = findViewById(R.id.studentsRv);
          noStudentTv = findViewById(R.id.noStudentTv);
          addStudentsButton = findViewById(R.id.addStudentsButton);
          classNameTv = findViewById(R.id.classNameTv);
          classDescTv = findViewById(R.id.classDescTv);

          collapsingToolbarLayout.setTitle(className);
          collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
          Random random = new Random();
          int i = random.nextInt(4);
          collapsingToolbarLayout.setBackgroundResource(customGradients[i]);
          studentsRv.setHasFixedSize(true);
          studentsRv.setLayoutManager(new LinearLayoutManager(this));

          studentListItems = new ArrayList<>();
          studentController = new StudentController(getApplicationContext());
          classController = new ClassController(MainActivity.db, getApplicationContext());

          addStudentsButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AddStudentsActivity.class);
                    intent.putExtra("CLASS_NAME", className);
                    startActivity(intent);
               }
          });
     }

     @Override
     public void onResume() {
          super.onResume();
          studentList = studentController.getAllStudents(className);

          Classes classObj = classController.getByName(className);
          int size = studentList.size();

          classNameTv.setText(className);
          classDescTv.setText(classObj.getClassDesc());

          studentListItems = new ArrayList<>();
          if(size > 0) {
               noStudentTv.setVisibility(View.GONE);
               for (int i = 0; i < size; i++) {
                    String name = studentList.get(i).getName();
                    String sno = studentList.get(i).getStudentNum();
                    int[] attendance = studentController.getStudentAttendance(classObj.getClassID(),sno);
                    studentListItems.add(new StudentListItem(name, sno, attendance[0], attendance[1], attendance[2]));
               }
          }else{
               noStudentTv.setVisibility(View.VISIBLE);
          }

          adapter = new StudentListAdapter(studentListItems, this);
          studentsRv.setAdapter(adapter);
     }
}
