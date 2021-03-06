/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): Ronnel Austria, Oliver Atienza
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.1 <13/04/2018> - Oliver Atienza
 *        - modified the toolbar to include back button
 *
 *   Version 1.0 <20/03/2018> - Ronnel Austria
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  EditClassActivity.java
 * @Creation Date: 20/03/18
 * @Version: 1.1
 */
package com.example.classattendancemobileapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.classattendancemobileapp.database.Classes;

public class EditClassActivity extends AppCompatActivity {

     CollapsingToolbarLayout collapsingToolbarLayout;
     FloatingActionButton editClassFAB; // the FloatingActionButton widget for editing a class
     EditText classNameEt; // the EditText widget for class name
     EditText classDescEt; // the EditText widget for class description
     Toolbar toolbar; // the Toolbar widget at the top of the view which houses the back button
     /**
      * onCreate() <20/03/2018>
      * - android function called when the activity is created
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: none
      */
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_edit_class);
          Intent intent = getIntent();
          String className = intent.getStringExtra("CLASS_NAME");

          toolbar = findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);

          if (getSupportActionBar() != null){
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
               getSupportActionBar().setDisplayShowHomeEnabled(true);
          }

          collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
          collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);

          final ClassController classController;
          classController = new ClassController(MainActivity.db, getApplicationContext());
          editClassFAB = findViewById(R.id.editClassFAB);
          classNameEt = findViewById(R.id.classNameEt);
          classDescEt = findViewById(R.id.classDescEt);
          Classes classObj = classController.getByName(className);
          final String oldClassName; // variable holder for old class name
          final String oldClassDesc; // variable holder for old class description
          oldClassName = classObj.getClassName();
          oldClassDesc = classObj.getClassDesc();
          classNameEt.setText(oldClassName);
          classDescEt.setText(oldClassDesc);
          editClassFAB.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               String newClassName; // variable holder for new class name
               String newClassDesc; // variable holder for new class description

               newClassName = classNameEt.getText().toString();
               newClassDesc = classDescEt.getText().toString();

               boolean b = classController.editClass(oldClassName, newClassName, oldClassDesc, newClassDesc);
               if(b){
                    finish();
               }


               }
          });


    }
}
