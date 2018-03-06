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
 *   Version 1.0 <25/02/2018> - John Oliver
 *        - created initial file
 *
 *   Version 1.1 <06/03/2018> - Arielle Gabriel
 *        - added getters for present, absent, and late
 *
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

     String name;
     String sno;
     int present;
     int late;
     int absent;

     public StudentListItem(String name, String sno, int present, int late, int absent) {
          this.name = name;
          this.sno = sno;
          this.present = present;
          this.late = late;
          this.absent = absent;
     }

     public String getName() {
          return name;
     }

     public String getSno() {
          return sno;
     }

     public int getPresent() {
          return present;
     }

     public int getLate() {
          return late;
     }

     public int getAbsent() {
          return absent;
     }
}
