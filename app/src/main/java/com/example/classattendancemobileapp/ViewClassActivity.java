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
 *
 *   Version 1.1 <22/02/2018> - John Oliver
 *        - used an instance of StudentController to finish the functional student ListView. Added code comments
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
 * @Version: 1.1
 */

package com.example.classattendancemobileapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.classattendancemobileapp.database.Student;

import java.util.List;

public class ViewClassActivity extends AppCompatActivity {

     ListView studentListView; // the ListView widget to display the list of students
     TextView noStudentTv; // the TextView widget to display the 'no students' notice
     TextView classNameTv; // the TextView widget to display the class name at the header
     TextView sectionTv; // the TextView widget to display the section of the class at the header
     Button addStudentsButton; // the Button widget linked to open a new AddStudentsActivity screen
     StudentController studentController; // the student controller object which is directly connected to the database
     List<Student> studentList; // the list of students returned by the controller
     String className;

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

          className = intent.getStringExtra("CLASS_NAME");
          setTitle(className);
          studentController = new StudentController(getApplicationContext());

          studentListView = findViewById(R.id.studentListView);
          noStudentTv = findViewById(R.id.noStudentTv);
          classNameTv = findViewById(R.id.classNameTv);
          sectionTv = findViewById(R.id.sectionTv);
          addStudentsButton = findViewById(R.id.addStudentsButton);

          classNameTv.setText(className);

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
          int size = studentList.size();
          final String[] studentNames = new String[size];
          final String[] studentNos = new String[size];
          if(studentNames.length == 0){
               noStudentTv.setVisibility(View.VISIBLE);
          }else{
               for(int i = 0; i < size; i++){
                    studentNames[i] = studentList.get(i).getName();
                    studentNos[i] = studentList.get(i).getStudentNum();
               }
          }

          ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, studentNames){
               @NonNull
               @Override
               public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView name = view.findViewById(android.R.id.text1);
                    TextView sno = view.findViewById(android.R.id.text2);

                    name.setText(studentNames[position]);
                    sno.setText(studentNos[position]);
                    return view;
               }
          };
          studentListView.setAdapter(adapter);
     }
}
