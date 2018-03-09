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
 *        - added code for toolbar to include back button
 *
 *   Version 1.11 <09/02/2018> - John Oliver
 *        - added class creation feedback (return to MainActivity after successful class creation.) Rearranged code
 *          for better readability
 *
 *   Version 1.1 <08/02/2018> - Ronnel Roi
 *        - added the lines for actual creation of classes. Switched the creation of database from inMemory to a persistent one.
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
 * @Creation Date: 07/02/18
 * @Version: 1.1
 */

package com.example.classattendancemobileapp;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.classattendancemobileapp.database.AppDatabase;

import java.util.Random;

public class CreateClassActivity extends AppCompatActivity {

     final int[] customGradients = {R.drawable.custom_gradient_1, R.drawable.custom_gradient_2, R.drawable.custom_gradient_3, R.drawable.custom_gradient_4, R.drawable.custom_gradient_5}; // array of custom-defined gradients to be set as widgets' background tint
     static AppDatabase db; // the application's main database
     ClassController classController;  // the class controller that interacts with the database
     CollapsingToolbarLayout collapsingToolbarLayout; // the CollapsingToolbarLayout which contains buttons and information about the class
     FloatingActionButton createClassFAB; // the FloatingActionButton widget for creating a class
     Toolbar toolbar; // the Toolbar widget which appears when the collapsingToolbarLayout is collapsed

     /**
      * onCreate() <07/02/2018>
      * - android function called when the activity is created
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: none
      */
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_create_class);
          buildDB();
          toolbar = findViewById(R.id.toolbar);
          toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
          setSupportActionBar(toolbar);

          classController = new ClassController(db, getApplicationContext());
          createClassFAB = findViewById(R.id.createClassFAB);
          collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

          Random random = new Random();
          int i = random.nextInt(5);
          collapsingToolbarLayout.setBackgroundResource(customGradients[i]);
          collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
          createClassFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               EditText classNameEt;
               EditText classDescEt;
               String className;
               String classDesc;
                 
               classNameEt = findViewById(R.id.classNameEt);
               classDescEt =  findViewById(R.id.classDescEt);
               className = classNameEt.getText().toString();
               classDesc = classDescEt.getText().toString();

               boolean b = classController.insertClass(className, classDesc);
               if(b)
                    finish();
            }
          });
     }

     /**
      * buildDB() <07/02/2018>
      * - builds the application's database
      * @param: none
      * @requires: none
      * @returns: none
      */
     public void buildDB(){
//        db = Room.inMemoryDatabaseBuilder(
//                getApplicationContext(),
//                AppDatabase.class).allowMainThreadQueries().build();
          db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "classattendance").allowMainThreadQueries().build();
     }
}
