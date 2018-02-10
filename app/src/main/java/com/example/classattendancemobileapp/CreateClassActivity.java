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
 *   Version 1.1 <08/02/2018> - Ronnel Roi
 *        - added the lines for actual creation of classes. Switched the creation of database from inMemory to a persistent one.
 *
 *   Version 1.11 <09/02/2018> - John Oliver
 *        - added class creation feedback (return to MainActivity after successful class creation.) Rearranged code
 *          for better readability
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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.AppDatabase;

public class CreateClassActivity extends AppCompatActivity {

     static AppDatabase db; // variable holder for the application's main database
     ClassController classController;  // variable holder for the class controller that interacts with the database
     Button createClassButton; // variable holder for the Button widget for creating a class

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
          setContentView(R.layout.create_class_activity);
          buildDB();
          classController = new ClassController(db, getApplicationContext());
          createClassButton = findViewById(R.id.createClassButton);
          createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               EditText classNameEditText, classDescEditText;
               String className, classDesc;

               classNameEditText = findViewById(R.id.classNameEditText);
               classDescEditText =  findViewById(R.id.classDescEditText);
               className = classNameEditText.getText().toString();
               classDesc = classDescEditText.getText().toString();

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
