/**
 * Class Attendance Mobile App
 *
 * “This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018”.
 *
 * @File Author(s): Ronnel Austria, Arielle Gabriel
 *
 * */

/**
 * Code History
 *    Version x.x <DD/MM/YYYY> - Author
 *         [description of changes]
 * Version 1.0 <07/03/2018> - Ronnel Austria
 *    - created initial file for attendance controller
 * Version 1.01 <07/03/2018> - Arielle Gabriel
 *    - minor changes in checking existing attendance records
 * */

/**
 * Class Attendance Mobile App
 *
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  AttendanceController.java
 * @Creation Date: 07/03/18
 * @Version: 1.01
 *
 * */
package com.example.classattendancemobileapp;

import android.content.Context;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.Attendance;
import com.example.classattendancemobileapp.database.Student;

import java.util.List;

/**
 * Created by User on 07/03/2018.
 */

public class AttendanceController {
     Context context; //variable holder for the running environment used
     public AttendanceController(Context context){
        this.context = context;
    }

     /**
     * addAttendance() <07/03/2018>
     * - add attendance controller
     * @param: classID - classID of the class to check attendance, attendanceDate - date of the attendance record, attendanceList - list containing the studentnumber and
      *                   attendance entry(Present, Late, Absent) of the student
     * @requires: none
     * @returns: Boolean value of true is operation is successful and false if otherwise
     */


     public Boolean addAttendance(int classID, String attendanceDate, List<String[]> attendanceList){
          for(int i = 0; i<attendanceList.size(); i++){
              int match;
              String studentNum = attendanceList.get(i)[0];
              String studentAttendance = attendanceList.get(i)[1];
              match = MainActivity.db.attendanceDao().countMatchAttendance(classID, studentNum, attendanceDate);
                if(match>0){
                    Toast.makeText(context, "Attendance Record for the date "+attendanceDate+" already exists! ", Toast.LENGTH_LONG).show();
                    return false;
                }
                else {
                    Attendance newAttendance = new Attendance(classID, studentNum, attendanceDate, studentAttendance);
                    MainActivity.db.attendanceDao().insert(newAttendance);
                }
         }
         return true;
     }

     public List<Attendance> getAttendance(int classID, String date){
          return MainActivity.db.attendanceDao().getbyDate(classID,date);
     }

     public List<String> getDates(int classID){
          return MainActivity.db.attendanceDao().listDates(classID);
     }
}
