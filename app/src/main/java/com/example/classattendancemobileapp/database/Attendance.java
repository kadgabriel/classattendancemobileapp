package com.example.classattendancemobileapp.database;

/*
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @Author(s): Arielle Gabriel
* @File:  Attendance.java
* @Version: 1.0
*
* */

/*
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.0 <04/02/2018> - Arielle Gabriel
*    - created initial file for attendance db entity
* */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*
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

class Attendance{
     @ColumnInfo(name = "classID")
     private int classID;

     @ColumnInfo(name = "studentNum")
     private String studentNum;

     @ColumnInfo(name = "date")
     private String date;

     @ColumnInfo(name = "entry")
     private String entry;

     /*
     * getClassID() <04/02/2018>
     * - getter function for classID attribute
     * @param: none
     * @requires: none
     * @returns: int - containing the class' ID
     * */
     public int getClassID(){
          return this.classID;
     }

     /*
     * getStudentNum() <04/02/2018>
     * - getter function for studentNumber attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the student number
     * */
     public String getStudentNum() {
          return this.studentNum;
     }

     /*
     * getDate() <04/02/2018>
     * - getter function for date attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the date
     * */
     public String getDate() {
          return this.date;
     }

     /*
     * getEntry() <04/02/2018>
     * - getter function for entry attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the entry value (values: A/L/P)
     * */
     public String getEntry() {
          return this.entry;
     }

     /*
     * setClassID() <04/02/2018>
     * - setter function for classID attribute
     * @param: int - classID to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassID(int classID){
          this.classID = classID;
     }

     /*
     * setStudentNum() <04/02/2018>
     * - setter function for studentNum attribute
     * @param: String - studentNum to be set
     * @requires: none
     * @returns: none
     * */
     public void setStudentNum(String studentNum) {
          this.studentNum = studentNum;
     }

     /*
     * setDate() <04/02/2018>
     * - setter function for date attribute
     * @param: String - date to be set
     * @requires: none
     * @returns: none
     * */
     public void setDate(String date) {
          this.date = date;
     }

     /*
     * setEntry() <04/02/2018>
     * - setter function for entry attribute
     * @param: String - entry to be set (values: A/L/P)
     * @requires: none
     * @returns: none
     * */
     public void setEntry(String entry) {
          this.entry = entry;
     }
}