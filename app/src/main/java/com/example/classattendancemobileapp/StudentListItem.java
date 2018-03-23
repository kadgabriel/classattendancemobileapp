/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): John Oliver, Arielle Gabriel
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.1 <06/03/2018> - Arielle Gabriel
 *        - added getters for present, absent, and late
 *
 *   Version 1.0 <25/02/2018> - John Oliver
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  StudentListItem.java
 * @Creation Date: 25/02/18
 * @Version: 1.1
 */

package com.example.classattendancemobileapp;

public class StudentListItem {

     String name; // name of the student
     String sno; // student number of the student
     int present; // int value number of times the student has been present
     int late; // int value number of times the student has been late
     int absent; // int value number of times the student has been absent

     /**
      * StudentListItem() <25/02/2018>
      * - the class constructor
      * @param: name - the student name to be displayed, sno - the student number of the student, present - number of times the student has been present, late - number of times the student has been late, absent - number of times the student has been absent
      * @requires: none
      * @returns: a new AttendanceListItem instance
      */
     public StudentListItem(String name, String sno, int present, int late, int absent) {
          this.name = name;
          this.sno = sno;
          this.present = present;
          this.late = late;
          this.absent = absent;
     }

     /**
      * getName() <25/02/2018>
      * - getter for the name field
      * @param: none
      * @requires: none
      * @returns: String
      */
     public String getName() {
          return name;
     }

     /**
      * getSno() <25/02/2018>
      * - getter for the sno field
      * @param: none
      * @requires: none
      * @returns: String
      */
     public String getSno() {
          return sno;
     }

     /**
      * getPresent() <06/03/2018>
      * - getter for the present field
      * @param: none
      * @requires: none
      * @returns: int - returns the number of present entries
      */
     public int getPresent() {
          return present;
     }

     /**
      * getLate() <06/03/2018>
      * - getter for the late field
      * @param: none
      * @requires: none
      * @returns: int - returns the number of late entries
      */
     public int getLate() {
          return late;
     }

     /**
      * getAbsent() <06/03/2018>
      * - getter for the absent field
      * @param: none
      * @requires: none
      * @returns: int - returns the number of absent entries
      */
     public int getAbsent() {
          return absent;
     }
}
