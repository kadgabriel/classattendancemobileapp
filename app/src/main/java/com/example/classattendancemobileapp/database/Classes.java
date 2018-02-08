/*
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

/*
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.0 <04/02/2018> - Arielle Gabriel
*    - created initial file for classes db entity
*
* */

/*
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  Classes.java
* @Version: 1.0
*
* */


package com.example.classattendancemobileapp.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*
* construct a table named "classes" with primary key: classID
* */
@Entity(tableName = "classes")
public class Classes{
     /* set the table's primary key and auto increment it*/
     @PrimaryKey(autoGenerate = true)
     @ColumnInfo(name = "classID")
     private int classID; // variable holder for an Classes record's classID column value

     @ColumnInfo(name = "className")
     private String className; // variable holder for an Classes record's className column value

     @ColumnInfo(name = "classDesc")
     private String classDesc; // variable holder for an Classes record's classDesc column value

     /* empty constructor */
     Classes(){}

     /*
     * Classes() <04/02/2018>
     * - constructor for a Classes class with a name and description
     * @param: name - name for the class, desc - description of the class
     * @requires: none
     * @returns: none
     * */
     public Classes(String name, String desc){
          this.className = name;
          this.classDesc = desc;
     }

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
     * getClassName() <04/02/2018>
     * - getter function for className attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the class' name
     * */
     public String getClassName(){
          return this.className;
     }

     /*
     * getClassDesc() <04/02/2018>
     * - getter function for classDesc attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the class' description
     * */
     public String getClassDesc(){
          return this.classDesc;
     }

     /*
     * setClassID() <04/02/2018>
     * - setter function for classID attribute
     * @param: classID - classID to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassID(int classID){
          this.classID = classID;
     }

     /*
     * setClassName() <04/02/2018>
     * - setter function for className attribute
     * @param: className - className to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassName(String className){
          this.className = className;
     }

     /*
     * setClassDesc() <04/02/2018>
     * - setter function for classDesc attribute
     * @param: classDesc - classDesc to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassDesc(String classDesc){
          this.classDesc = classDesc;
     }


}