/**
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @File Author(s): Arielle Gabriel
*
* */


/**
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.1 <06/03/2018> - Arielle Gabriel
*    - made the file public
*
* Version 1.0 <04/02/2018> - Arielle Gabriel
*    - created initial file for attendance db entity
*
* */

/**
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  Attendance.java
* @Creation Date: 04/02/18
* @Version: 1.1
*
* */
package com.example.classattendancemobileapp.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
* construct a table named "attendance" with primary keys: classID, studentNum, and date
* with foreign key classID from table "classes"
* */
@Entity(
          tableName = "attendance",
          primaryKeys = {"classID", "studentNum", "date"},
          foreignKeys = {
                    @ForeignKey(entity = Classes.class,
                         parentColumns = "classID",
                         childColumns = "classID",
                         onDelete = CASCADE,
                         onUpdate = CASCADE),
                    }
)

public class Attendance{
     @ColumnInfo(name = "classID")
     private int classID; // variable holder for an Attendance record's classID column value

     @ColumnInfo(name = "studentNum")
     private String studentNum; // variable holder for an Attendance record's studentNum column value

     @ColumnInfo(name = "date")
     private String date; // variable holder for an Attendance record's date column value

     @ColumnInfo(name = "entry")
     private String entry; // variable holder for an Attendance record's entry column value

     /**
     * getClassID() <04/02/2018>
     * - getter function for classID attribute
     * @param: none
     * @requires: none
     * @returns: int - containing the class' ID
     * */

     public Attendance(int classID, String studentNum, String attendanceDate, String studentAttendance){
         this.classID = classID;
         this.studentNum = studentNum;
         this.date = attendanceDate;
         this.entry = studentAttendance;
     }

     public int getClassID(){
          return this.classID;
     }

     /**
     * getStudentNum() <04/02/2018>
     * - getter function for studentNumber attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the student number
     * */
     public String getStudentNum() {
          return this.studentNum;
     }

     /**
     * getDate() <04/02/2018>
     * - getter function for date attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the date
     * */
     public String getDate() {
          return this.date;
     }

     /**
     * getEntry() <04/02/2018>
     * - getter function for entry attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the entry value (values: A/L/P)
     * */
     public String getEntry() {
          return this.entry;
     }

     /**
     * setClassID() <04/02/2018>
     * - setter function for classID attribute
     * @param: classID - class ID to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassID(int classID){
          this.classID = classID;
     }

     /**
     * setStudentNum() <04/02/2018>
     * - setter function for studentNum attribute
     * @param: studentNum - student number to be set
     * @requires: none
     * @returns: none
     * */
     public void setStudentNum(String studentNum) {
          this.studentNum = studentNum;
     }

     /**
     * setDate() <04/02/2018>
     * - setter function for date attribute
     * @param: date - date to be set
     * @requires: none
     * @returns: none
     * */
     public void setDate(String date) {
          this.date = date;
     }

     /**
     * setEntry() <04/02/2018>
     * - setter function for entry attribute
     * @param: entry - entry to be set (values: A/L/P)
     * @requires: none
     * @returns: none
     * */
     public void setEntry(String entry) {
          this.entry = entry;
     }
}