package com.example.classattendancemobileapp;

import android.content.Context;

import com.example.classattendancemobileapp.database.Attendance;

import java.util.List;

/**
 * Created by User on 07/03/2018.
 */

public class AttendanceController {
     Context context; //variable holder for the running environment used
     public AttendanceController(Context context){
        this.context = context;
    }

     public void addAttendance(int classID, String attendanceDate, List<String[]> attendanceList){
          for(int i = 0; i<attendanceList.size(); i++){
              String studentNum = attendanceList.get(i)[0];
              String studentAttendance = attendanceList.get(i)[1];
              Attendance newAttendance = new Attendance(classID, studentNum , attendanceDate, studentAttendance);
              MainActivity.db.attendanceDao().insert(newAttendance);
         }

     }
}
