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
 *   Version 1.01 <08/02/2018> - John Oliver
 *        - added lines event listeners on ListView item select
 *   Version 1.10 <08/02/2018> - Ronnel Roi
 *        - transferred lines for getting classes and displaying classes to onResume() to ensure the data displayed
 *          is up to date all the time. Switched the creation of database from inMemory to a persistent one.
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  MainActivity.java
 * @Version: 1.10
 */

package com.example.classattendancemobileapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.classattendancemobileapp.database.AppDatabase;
import com.example.classattendancemobileapp.database.Classes;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     static AppDatabase db; // variable holder for the application's database
     ListView classListView; // variable holder for the ListView widget to display the list of classes
     FloatingActionButton addClassFAB; // variable holder for the floating action button on the screen
     ClassController classController; // variable holder for the class controller that interacts with the database

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
          setContentView(R.layout.activity_main);
          buildDB();

          classListView = findViewById(R.id.classListView);
          addClassFAB = findViewById(R.id.addClassFAB);

          classController = new ClassController(db, getApplicationContext());
     }

     /**
      * onResume() <07/02/2018>
      * - android function called when the paused activity is brought back to foreground
      * @param: none
      * @requires: none
      * @returns: none
      */
     @Override
     public void onResume(){
          super.onResume();

          //Get info of classes from the database
          List<Classes> classList = classController.getAllClasses();
          final String[] classNames = new String[classList.size()];
          for(int i = 0; i < classList.size(); i++){
               classNames[i] = classList.get(i).getClassName();
          }

          ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, classNames);

          classListView.setAdapter(adapter);

          addClassFAB = findViewById(R.id.addClassFAB);

          //Added listview item click listener
          classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(getApplicationContext(), ViewClassActivity.class);
               intent.putExtra("CLASS_NAME", classNames[(int) l]);
               startActivity(intent);
            }
          });

          addClassFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateClassActivity.class);
                startActivity(intent);
            }
          });
     }

     /**
      * buildDB() <08/02/2018>
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
