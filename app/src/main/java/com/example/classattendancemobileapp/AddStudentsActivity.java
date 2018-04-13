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
 *   Version 1.2 <26/02/2018> - John Oliver
 *        - added randomization of the toolbar's gradient background
 *
 *   Version 1.1 <22/02/2018> - John Oliver
 *        - functional add student/s and file picker. Added code comments. Removed blank lines and fixed redundant commands
 *
 *   Version 1.0 <07/02/2018> - John Oliver
 *        - created initial file
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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;


public class AddStudentsActivity extends AppCompatActivity {
     private static final int PICKFILE_RESULT_CODE = 1; // integer code to determine the id of most recent fragment that finished its cycle

     final int[] customGradients = {R.drawable.custom_gradient_1, R.drawable.custom_gradient_2, R.drawable.custom_gradient_3, R.drawable.custom_gradient_4, R.drawable.custom_gradient_5}; // array of custom-defined gradients to be set as widgets' background tint
     Button confirmAddStudentsButton; // the Button widget to confirm adding student/s
     Button pathButton; // the Button widget to open a file picker activity
     EditText firstNameEt; // the EditText widget for the first name of the single student being added
     EditText lastNameEt; // the EditText widget for the last name of the single student being added
     EditText pathEt; // the EditText widget for path of the CSV file
     EditText snoEt; // the EditText widget for the student number of the single student being added
     RadioButton csvRb; // the RadioButton widget for when adding multiple students through a CSV file
     RadioButton singleStudentRb; // the RadioButton widget for when adding a single student
     StudentController studentController; // the student controller object which is directly connected to the database
     TextView title;
     Toolbar toolbar;
     private static String FILEPATH; // the string value of the path of the object returned by the file picker activity
     private static Uri selectedFile; // the selected file from the file picker

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

          toolbar = findViewById(R.id.toolbar);
          title = toolbar.findViewById(R.id.title);
          title.setText("Add Students");
          setSupportActionBar(toolbar);

          if (getSupportActionBar() != null){
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
               getSupportActionBar().setDisplayShowHomeEnabled(true);
          }

          confirmAddStudentsButton = findViewById(R.id.confirmAddStudentsButton);
          csvRb = findViewById(R.id.csvRb);
          firstNameEt = findViewById(R.id.firstNameEt);
          lastNameEt = findViewById(R.id.lastNameEt);
          pathButton = findViewById(R.id.pathButton);
          pathEt = findViewById(R.id.pathEt);
          singleStudentRb = findViewById(R.id.singleStudentRb);
          snoEt = findViewById(R.id.snoEt);

          firstNameEt.setEnabled(false);
          lastNameEt.setEnabled(false);
          snoEt.setEnabled(false);

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
                    filePickerIntent.setType("text/comma-separated-values");
                    filePickerIntent = filePickerIntent.createChooser(filePickerIntent, "Choose CSV file");
                    startActivityForResult(filePickerIntent, PICKFILE_RESULT_CODE);
               }
          });

          Random random = new Random();
          confirmAddStudentsButton.setBackgroundResource(customGradients[random.nextInt(5)]);
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