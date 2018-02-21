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
* Version 1.01 <17/02/2018> - Arielle Gabriel
*    - added constructor
*
* Version 1.0 <04/02/2018> - Arielle Gabriel
*    - created initial file for student db entity
* */

/*
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  Student.java
* @Creation Date: 04/02/18
* @Version: 1.01
*
* */

package com.example.classattendancemobileapp.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*
* construct a table named "student" with primary keys: classID, and studentNum
* with foreign key classID from table classes
* */
@Entity(
          tableName = "student",
          primaryKeys = {"classID", "studentNum"},
          foreignKeys = @ForeignKey(entity = Classes.class,
                    parentColumns = "classID",
                    childColumns = "classID",
                    onDelete=CASCADE,
                    onUpdate=CASCADE)
	     )
class Student {
     @ColumnInfo(name = "classID")
     private int classID; // variable holder for an Student record's classID column value

     @ColumnInfo(name = "studentNum")
     private String studentNum; // variable holder for an Student record's studentNum column value

     @ColumnInfo(name = "firstName")
     private String firstName; // variable holder for an Student record's firstName column value

     @ColumnInfo(name = "lastName")
     private String lastName; // variable holder for an Student record's lastName column value

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
     * Student() <17/02/2018>
     * - constructor for a Classes class with a name and description
     * @param: int - classID, String - student number, String - first name, String last name
     * @requires: none
     * @returns: none
     * */
     public Student(int classID, String studentNum, String firstName, String lastName){
          this.classID = classID;
          this.studentNum = studentNum;
          this.firstName = firstName;
          this.lastName = lastName;
     }

     /*
     * getStudentNum() <04/02/2018>
     * - getter function for studentNumber attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the student number
     * */
     public String getStudentNum(){
          return this.studentNum;
     }

     /*
     * getFirstName() <04/02/2018>
     * - getter function for firstName attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the student's first name
     * */
     public String getFirstName(){
          return this.firstName;
     }

     /*
     * getLastName() <04/02/2018>
     * - getter function for lastName attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the student's last name
     * */
     public String getLastName(){
          return this.lastName;
     }

     /*
     * getName() <04/02/2018>
     * - getter function for lastName and firstName attribute
     * @param: none
     * @requires: none
     * @returns: String - containing the concatenation of student's
     *                    "last name, first name"
     * */
     public String getName(){
          return this.lastName+", "+this.firstName;
     }

     /*
     * setClassID() <04/02/2018>
     * - setter function for classID attribute
     * @param: classID - classID of the class where the student belong
     * @requires: none
     * @returns: none
     * */
     public void setClassID(int classID){
          this.classID = classID;
     }

     /*
     * setStudentNum() <04/02/2018>
     * - setter function for studentNum attribute
     * @param: studentNum - student number
     * @requires: none
     * @returns: none
     * */
     public void setStudentNum(String studentNum){
          this.studentNum = studentNum;
     }

     /*
     * setFirstName() <04/02/2018>
     * - setter function for firstName attribute
     * @param: firstName - student's first name
     * @requires: none
     * @returns: none
     * */
     public void setFirstName(String firstName){
          this.firstName = firstName;
     }

     /*
     * setLastName() <04/02/2018>
     * - setter function for lastName attribute
     * @param: lastName - student's last name
     * @requires: none
     * @returns: none
     * */
     public void setLastName(String lastName){
          this.lastName = lastName;
     }



}