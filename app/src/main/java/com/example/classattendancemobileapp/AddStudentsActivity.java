/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): John Oliver, Ronnel Roi
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.0 <07/02/2018> - John Oliver
 *        - created initial file
 *
 *   Version 1.1 <22/02/2018> - John Oliver
 *        - functional add student/s and file picker. Added code comments. Removed blank lines and fixed redundant commands
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  AddStudentsActivity.java
 * @Creation Date: 19/02/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;


public class AddStudentsActivity extends AppCompatActivity {
     private static final int PICKFILE_RESULT_CODE = 1;

     RadioButton singleStudentRb; // the RadioButton widget for when adding a single student
     RadioButton csvRb; // the RadioButton widget for when adding multiple students through a CSV file
     EditText firstNameEt; // the EditText widget for the first name of the single student being added
     EditText lastNameEt; // the EditText widget for the last name of the single student being added
     EditText snoEt; // the EditText widget for the student number of the single student being added
     EditText pathEt; // the EditText widget for path of the CSV file
     Button pathButton; // the Button widget to open a file picker activity
     Button confirmAddStudentsButton; // the Button widget to confirm adding student/s
     StudentController studentController; // the student controller object which is directly connected to the database
     private static String FILEPATH; // the string value of the path of the object returned by the file picker activity
     private static Uri selectedFile;

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
          setContentView(R.layout.activity_add_students);
          studentController = new StudentController(getApplicationContext());
          Intent intent = getIntent();
          final String className;
          className = intent.getStringExtra("CLASS_NAME");

          singleStudentRb = findViewById(R.id.singleStudentRb);
          csvRb = findViewById(R.id.csvRb);

          firstNameEt = findViewById(R.id.firstNameEt);
          lastNameEt = findViewById(R.id.lastNameEt);
          snoEt = findViewById(R.id.snoEt);
          pathEt = findViewById(R.id.pathEt);

          pathButton = findViewById(R.id.pathButton);
          confirmAddStudentsButton = findViewById(R.id.confirmAddStudentsButton);

          pathButton.setEnabled(false);

          singleStudentRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                         csvRb.setChecked(false);
                         pathButton.setEnabled(false);
                         firstNameEt.setEnabled(true);
                         lastNameEt.setEnabled(true);
                         snoEt.setEnabled(true);
                    }

               }
          });

          csvRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                         singleStudentRb.setChecked(false);
                         pathButton.setEnabled(true);
                         firstNameEt.setEnabled(false);
                         lastNameEt.setEnabled(false);
                         snoEt.setEnabled(false);
                    }

               }
          });


          pathButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent filePickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                    filePickerIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    filePickerIntent.setType("text/comma-separated-values");
                    filePickerIntent = filePickerIntent.createChooser(filePickerIntent, "Choose CSV file");
                    startActivityForResult(filePickerIntent, PICKFILE_RESULT_CODE);
               }
          });

          confirmAddStudentsButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if(singleStudentRb.isChecked()){
                         String firstName, lastName, studentNumber;

                         firstName = firstNameEt.getText().toString();
                         lastName = lastNameEt.getText().toString();
                         studentNumber = snoEt.getText().toString();
                         studentController.insertStudent(className, firstName, lastName, studentNumber);

                    }
                    else{
                         studentController.insertMultipleStudents(className,selectedFile);
                    }

               }
          });
     }

     /**
      * onActivityResult() <08/02/2018>
      * - android function called when an activity has finished its task
      * @param: requestCode - request code of the activity that has just finished, used to identify the type of activity, resultCode - code to identify what kind of result was returned, data - data passed, through an intent, by the previous activity
      * @requires: none
      * @returns: none
      */
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
          switch(requestCode){
               case PICKFILE_RESULT_CODE:
                    if(resultCode == RESULT_OK) {
                         selectedFile = data.getData();
                         FILEPATH = data.getData().getPath();
                         pathEt.setText(FILEPATH);
                    }
          }
     }
}