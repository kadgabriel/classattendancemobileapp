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
 * @File:  ClassListItem.java
 * @Creation Date: 25/02/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

public class ClassListItem {
     String name; // name of the class
     String desc; // description of the class

     /**
      * ClassListItem() <25/02/2018>
      * - the class constructor
      * @param: name - the class name to be displayed, desc - short description of the class
      * @requires: none
      * @returns: a new AttendanceListItem instance
      */
     public ClassListItem(String name, String desc) {
          this.name = name;
          this.desc = desc;
     }

     /**
      * getName() <25/02/2018>
      * - getter for the name field
      * @param: none
      * @requires: none
      * @returns: the name field
      */
     public String getName() {
          return name;
     }

     /**
      * getDesc() <25/02/2018>
      * - getter for the desc field
      * @param: none
      * @requires: none
      * @returns: the desc field
      */
     public String getDesc() {
          return desc;
     }
}
