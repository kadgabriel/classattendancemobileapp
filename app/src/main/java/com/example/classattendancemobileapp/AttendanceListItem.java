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
 *   Version 1.0 <07/03/2018> - John Oliver
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  AttendanceListItem.java
 * @Creation Date: 07/03/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

public class AttendanceListItem {
     String name; // name of the student in the entry
     String sno; // student number of the student in the entry

     /**
      * AttendanceListItem() <07/03/2018>
      * - the class constructor
      * @param: name - the student name to be displayed, sno - the student number of the student
      * @requires: none
      * @returns: a new AttendanceListItem instance
      */
     public AttendanceListItem(String name, String sno) {
          this.name = name;
          this.sno = sno;
     }

     /**
      * getName() <07/03/2018>
      * - getter for the name field
      * @param: none
      * @requires: none
      * @returns: String
      */
     public String getName() {
          return name;
     }

     /**
      * getSno() <07/03/2018>
      * - getter for the student number field
      * @param: none
      * @requires: none
      * @returns: String
      */
     public String getSno() {
          return sno;
     }
}
