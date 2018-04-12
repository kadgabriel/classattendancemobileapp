/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): John Oliver, Arielle Gabriel, Ronnel Austria
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.7 <23/03/2018> - Oliver Atienza
 *        - added edit error detection (when either of the edit fields are empty)
 *
 *   Version 1.6 <21/03/2018> - Ronnel Austria 
 *        - added listener for edit class info button and used classID instead of className in getting the class object
 *
 *   Version 1.5 <20/03/2018> - Oliver Atienza
 *        - now implements EditStudentDialogFragment.EditStudentDialogListener to be able to edit students' information
 *
 *   Version 1.4 <15/03/2018> - Arielle Gabriel
 *        - added listener for view attendance button
 *
 *   Version 1.3 <06/03/2018> - Arielle Gabriel
 *        - added attendance count
 *
 *   Version 1.2 <23/02/2018> - Oliver Atienza
 *        - fixed a bug
 *
 *   Version 1.1 <22/02/2018> - Oliver Atienza
 *        - used an instance of StudentController to finish the functional student ListView. Added code comments
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
 * @Version: 1.7
 */

package com.example.classattendancemobileapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.Classes;
import com.example.classattendancemobileapp.database.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViewClassActivity extends AppCompatActivity implements EditStudentDialogFragment.EditStudentDialogListener{

     final int[] customGradients = {R.drawable.custom_gradient_1, R.drawable.custom_gradient_2, R.drawable.custom_gradient_3, R.drawable.custom_gradient_4, R.drawable.custom_gradient_5};
     int classID; // variable holder for classID
     int position = 0; // the position of the adapter item currently being edited

     Button addAttendanceButton; // Button widget for the add attendance record
     Button addStudentsButton; // Button widget for the add students
     Button viewAttendanceButton; // Button widget for the view attendace
     Button editClassButton; // Button widget for edit class info
     Button deleteClassButton; // Button widget for delete class
     ClassController classController; // // the class controller object which is directly connected to the database
     CollapsingToolbarLayout collapsingToolbarLayout; // the CollapsingToolbarLayout which contains buttons and information about the class
     List<Student> studentList; // the list of students returned by the controller
     List<StudentListItem> studentListItems; // the list of students to be processed by the adapter
     RecyclerView studentsRv; // RecyclerView widget to display the list of students in the class
     RecyclerView.Adapter adapter; // adapter object to translate student data into UI objects in the attendance recycler view widget
     String className; // the name of the class selected from MainActivity
     Student selectedStudent; // holder for the student object that is currently being edited
     StudentController studentController; // the student controller object which is directly connected to the database
     TextView classDescTv; // the TextView widget that displays the short description of the class
     TextView classNameTv; // the TextView widget that displays the name of the class
     TextView noStudentTv; // the TextView widget that display a tooltip if the class has no students
     Toolbar toolbar; // the Toolbar widget at the top of the view

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
          Intent intent = getIntent();
          toolbar = findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);

          if (getSupportActionBar() != null){
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
               getSupportActionBar().setDisplayShowHomeEnabled(true);
          }

          className = intent.getStringExtra("CLASS_NAME");

          collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
          studentsRv = findViewById(R.id.studentsRv);
          noStudentTv = findViewById(R.id.noStudentTv);
          addAttendanceButton = findViewById(R.id.addAttendanceButton);
          addStudentsButton = findViewById(R.id.addStudentsButton);
          viewAttendanceButton = findViewById(R.id.viewAttendanceButton);
          editClassButton = findViewById(R.id.editClassButton);
          deleteClassButton = findViewById(R.id.deleteClassButton);
          classNameTv = findViewById(R.id.classNameTv);
          classDescTv = findViewById(R.id.classDescTv);

          collapsingToolbarLayout.setTitle(className);
          collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
          Random random = new Random();
          int i = random.nextInt(4);
          collapsingToolbarLayout.setBackgroundResource(customGradients[i]);
          studentsRv.setHasFixedSize(true);
          studentsRv.setLayoutManager(new LinearLayoutManager(this));

          studentListItems = new ArrayList<>(); // empty list of studentsListItems
          studentController = new StudentController(getApplicationContext());
          classController = new ClassController(MainActivity.db, getApplicationContext());
          Classes classObj;
          classObj = classController.getByName(className);
          classID = classObj.getClassID();
          addStudentsButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AddStudentsActivity.class);
                    intent.putExtra("CLASS_NAME", className);
                    startActivity(intent);
               }
          });

          addAttendanceButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddAttendanceActivity.class);
                    intent.putExtra("CLASS_NAME", className);
                    startActivity(intent);
               }
          });

          viewAttendanceButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ViewAttendanceActivity.class);
                    intent.putExtra("CLASS_NAME", className);
                    startActivity(intent);
               }
          });


         editClassButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), EditClassActivity.class);
                    intent.putExtra("CLASS_NAME", className);
                    startActivity(intent);
               }
          });

         deleteClassButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  new AlertDialog.Builder(ViewClassActivity.this)
                          .setTitle("Delete Class")
                          .setMessage("You are about to delete a class. Are you sure?")
                          .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                           boolean b;
                           b = classController.deleteClass(className);
                           if(b){
                                finish();
                           }
                      }
                  })
                          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int which) {
                          // do nothing
                      }
                  })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                          .show();

              }
         });


          studentsRv.addOnItemTouchListener(new RecyclerViewOnTouchListener(this, studentsRv, new ClickListener() {
               /**
                * onClick() <20/03/2018>
                * - callback function when the object implementing this interface detects a click event
                * @param: view - the view that was clicked, position - position of the view in the list
                * @requires: none
                * @returns: none
                */
               @Override
               public void onClick(View view, int position) {
                    Log.d("STUDENTS_RECYCLERVIEW", String.valueOf(position));
               }

               /**
                * onLongClick() <20/03/2018>
                * - callback function when the object implementing this interface detects a click event
                * @param: view - the view that was clicked, position - position of the view in the list
                * @requires: none
                * @returns: none
                */
               @Override
               public void onLongClick(View view, int position) {
                    selectedStudent = studentList.get(position);
                    ViewClassActivity.this.position = position;
                    String[] studentInfo = {selectedStudent.getFirstName(), selectedStudent.getLastName(), selectedStudent.getStudentNum()};
                    DialogFragment dialogFragment = EditStudentDialogFragment.newInstance(studentInfo);
                    dialogFragment.show(getSupportFragmentManager(), "edit_student");
               }
          }));

     }

     /**
      * onResume() <07/02/2018>
      * - android function called when the paused activity is brought back to foreground
      * @param: none
      * @requires: none
      * @returns: none
      */
     @Override
     public void onResume() {
          super.onResume();
          Classes classObj = MainActivity.db.classesDao().getByID(classID);
          className = classObj.getClassName();
          studentList = studentController.getAllStudents(className);


          int size = studentList.size();  // number of student's in the class

          classNameTv.setText(className);
          classDescTv.setText(classObj.getClassDesc());

          studentListItems = new ArrayList<>(); // empty list of studentsListItems
          if(size > 0) {
               noStudentTv.setVisibility(View.GONE);
               for (int i = 0; i < size; i++) {
                    String name = studentList.get(i).getName();  // variable holder for the student's name
                    String sno = studentList.get(i).getStudentNum(); // variable holder for the student's student number
                    int[] attendance = studentController.getStudentAttendance(classObj.getClassID(),sno); // variable holder for attendance stats of the student
                    studentListItems.add(new StudentListItem(name, sno, attendance[0], attendance[1], attendance[2]));
               }
          }else{
               noStudentTv.setVisibility(View.VISIBLE);
          }

          adapter = new StudentListAdapter(studentListItems, this);
          studentsRv.setAdapter(adapter);
     }

     /**
      * onDialogPositiveClick() <20/03/2018>
      * - a callback function when the dialog receives a positive confirmation
      * @param: studentInfo - string array containing the information about the student being edited
      * @requires: none
      * @returns: none
      */
     @Override
     public void onDialogPositiveClick(String[] studentInfo) {
          boolean b = studentController.updateStudent(classController.getByName(className).getClassID(), selectedStudent.getStudentNum(), studentInfo);
//          Classes classObj = classController.getByName(className);
//          Student student = studentController.getStudent(classObj.getClassID(), studentInfo[2]);
//          if(b) {
//               studentList.set(position, student);
//               int[] attendance = studentController.getStudentAttendance(classObj.getClassID(), student.getStudentNum());
//               studentListItems.set(position, new StudentListItem(student.getName(), student.getStudentNum(), attendance[0], attendance[1], attendance[2]));
//               adapter.notifyItemChanged(position);
//               Toast.makeText(getApplicationContext(), "Student information successfully edited!", Toast.LENGTH_SHORT).show();
//          }else{
//               Toast.makeText(getApplicationContext(), "Fields can not be empty!", Toast.LENGTH_SHORT).show();
//          }

          if(b) {
               Toast.makeText(getApplicationContext(), "Student information successfully edited!", Toast.LENGTH_SHORT).show();
               onResume();
          }else{
               Toast.makeText(getApplicationContext(), "Fields can not be empty!", Toast.LENGTH_SHORT).show();
          }
     }
}
