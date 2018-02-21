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
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  CreateClassActivity.java
 * @Creation Date: 19/02/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentsActivity extends AppCompatActivity {
     private static final int PICKFILE_RESULT_CODE = 1;

     RadioButton singleStudentRb, csvRb;
     EditText firstNameEt, lastNameEt, snoEt, pathEt;
     Button pathButton, confirmAddStudentsButton;

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

          singleStudentRb = findViewById(R.id.singleStudentRb);
          csvRb = findViewById(R.id.csvRb);

          firstNameEt = findViewById(R.id.firstNameEt);
          lastNameEt = findViewById(R.id.lastNameEt);
          snoEt = findViewById(R.id.snoEt);
          pathEt = findViewById(R.id.pathEt);

          pathButton = findViewById(R.id.pathButton);
          confirmAddStudentsButton = findViewById(R.id.confirmAddStudentsButton);

          singleStudentRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                         csvRb.setChecked(false);
               }
          });

          csvRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                         singleStudentRb.setChecked(false);
               }
          });

          pathButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Intent filePickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    filePickerIntent.setType("text/csv, text/comma-separated-values, application/csv");
                    filePickerIntent = filePickerIntent.createChooser(filePickerIntent, "Choose CSV file");
                    startActivityForResult(filePickerIntent, PICKFILE_RESULT_CODE);
               }
          });
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
          switch(requestCode){
               case PICKFILE_RESULT_CODE:
                    if(resultCode == RESULT_OK) {
                         String path = data.getData().getPath();
                         Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
                    }
          }
     }
}
