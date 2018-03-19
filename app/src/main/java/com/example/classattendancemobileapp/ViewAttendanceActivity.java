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
 *   Version 1.0 <15/02/2018> - Arielle Gabriel
 *        - created initial file based on AddAttendanceActivity.java
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  AddAttendanceActivity.java
 * @Creation Date: 15/03/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.*;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewAttendanceActivity extends AppCompatActivity {

     AttendanceController attendanceController; // the attendance controller object which is directly connected to the database
     Button confirmAddAttendanceButton; // the Button widget to confirm the creation of an attendance record
     ClassController classController; // the class controller object which is directly connected to the database
     List<Student> studentList; // list of students in the class of the attendance being made
     List<AttendanceListItem> attendanceListItems; // the student data that the adapter object translates into UI objects
     List<String[]> attendanceEntries; // data obtained which contains records about the attendance record being made
     RecyclerView attendanceRv; // the RecyclerView widget that displays the list of students
     RecyclerView.Adapter adapter; // adapter object to translate data into UI objects in the attendance recycler view widget
     String className; // the name of the class selected
     String dateString; // holds the string value of the date of the attendance record
     String dayString; // the name of the day of the week of the attendance record
     StudentController studentController; // the student controller object which is directly connected to the database
     TextView dateTv; // TextView widget to display the date of the attendance record
     TextView dayTv; // TextView widget to display the name of the day of the attendance record
     TextView emptyEntryTv; // TextView to display "No attendance entry for this day"
     /**
      * onCreate() <07/03/2018>
      * - android function called when the activity is created
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: none
      */
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_view_attendance);

          Intent intent = getIntent();
          className = intent.getStringExtra("CLASS_NAME");

          Date date = new Date();
          SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd");
          dateString = dateFormat.format(date.getTime());
          SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
          dayString = dayFormat.format(date.getTime());

          attendanceRv = findViewById(R.id.attendanceRv);
          confirmAddAttendanceButton = findViewById(R.id.confirmAddAttendanceButton);
          dateTv = findViewById(R.id.dateTv);
          dayTv = findViewById(R.id.dayTv);
          emptyEntryTv = findViewById(R.id.emptyEntryTv);

          attendanceRv.setHasFixedSize(true);
          attendanceRv.setLayoutManager(new LinearLayoutManager(this));
          dateTv.setText(dateString);
          dayTv.setText(dayString);

          attendanceController = new AttendanceController(getApplicationContext());
          classController = new ClassController(MainActivity.db, getApplicationContext());
          studentController = new StudentController(getApplicationContext());

          confirmAddAttendanceButton.setText("EDIT ATTENDANCE RECORD");
//          confirmAddAttendanceButton.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view) {
//                    attendanceEntries = new ArrayList<>();
//                    for(int i = 0; i < attendanceRv.getChildCount(); i++){
//                         AttendanceListAdapter.ViewHolder viewHolder = (AttendanceListAdapter.ViewHolder) attendanceRv.findViewHolderForAdapterPosition(i);
//                         String[] entry = {viewHolder.snoTv.getText().toString(), viewHolder.statusTv.getText().toString().substring(0,1)};
//                         attendanceEntries.add(entry);
//                    }
//                    if(attendanceController.addAttendance(classController.getByName(className).getClassID(), dateString, attendanceEntries) == true){
//                         Toast.makeText(getApplicationContext(), "Attendance record successfully created!", Toast.LENGTH_LONG).show();
//                         finish();
//                    }
//
//               }
//          });

          dateTv.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int date = calendar.get(Calendar.DAY_OF_MONTH);

                    final DatePickerDialog datePickerDialog = new DatePickerDialog(ViewAttendanceActivity.this, new DatePickerDialog.OnDateSetListener() {
                         @Override
                         public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                              Date selectedDate = new Date(year,month,date);
                              SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd");
                              dateString = dateFormat.format(selectedDate);
                              SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                              dayString = dayFormat.format(selectedDate.getTime()+selectedDate.getTimezoneOffset());

                              dateTv.setText(dateString);
                              dayTv.setText(dayString);
                              updateTv();
                         }
                    }, year, month, date);
                    datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                    datePickerDialog.show();
               }
          });

          dateTv.performClick();

     }

     /**
      * onResume() <07/03/2018>
      * - android function called when the paused activity is brought back to foreground
      * @param: none
      * @requires: none
      * @returns: none
      */
     @Override
     protected void onResume() {
          super.onResume();

     }

     public void updateTv(){
          Log.d("Attendance Date", dateTv.getText().toString());
          studentList = studentController.getAllStudents(className);
          attendanceListItems = new ArrayList<>();
          for (int i = 0; i < studentList.size(); i++) {
               Classes classobj = classController.getByName(className);
               String entry = MainActivity.db.attendanceDao().getbyDateandStud(classobj.getClassID(), dateTv.getText().toString(), studentList.get(i).getStudentNum());
               if(entry != null) {
                    attendanceListItems.add(new AttendanceListItem(studentList.get(i).getName(), studentList.get(i).getStudentNum(), entry));
               }
               Log.d("Attendance", studentList.get(i).getName()+""+entry);
          }

          if(attendanceListItems.isEmpty()){
               emptyEntryTv.setVisibility(View.VISIBLE);
          }
          else{
               emptyEntryTv.setVisibility(View.GONE);
          }

          adapter = new AttendanceListAdapter(attendanceListItems, this, false);
          attendanceRv.setAdapter(adapter);
     }
}
