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
* @File:  Classes.java
* @Version: 1.0
*
* */

/*
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.0 <04/02/2018> - Arielle Gabriel
*    - created initial file for classes db entity
* */

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
     private int classID;

     @ColumnInfo(name = "className")
     private String className;
     @ColumnInfo(name = "classDesc")
     private String classDesc;

     /* empty constructor */
     Classes(){}

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
     * @param: int - classID to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassID(int classID){
          this.classID = classID;
     }

     /*
     * setClassName() <04/02/2018>
     * - setter function for className attribute
     * @param: String - className to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassName(String className){
          this.className = className;
     }

     /*
     * setClassDesc() <04/02/2018>
     * - setter function for classDesc attribute
     * @param: String - classDesc to be set
     * @requires: none
     * @returns: none
     * */
     public void setClassDesc(String classDesc){
          this.classDesc = classDesc;
     }


}