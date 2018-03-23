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
 *   Version 1.0 <20/03/2018> - John Oliver
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  ClickListener.java
 * @Creation Date: 20/03/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.view.View;

public interface ClickListener {

     /**
      * onClick() <20/03/2018>
      * - callback function when the object implementing this interface detects a click event
      * @param: view - the view that was clicked, position - position of the view in the list
      * @requires: none
      * @returns: none
      */
     void onClick(View view, int position);

     /**
      * onLongClick() <20/03/2018>
      * - callback function when the object implementing this interface detects a click event
      * @param: view - the view that was clicked, position - position of the view in the list
      * @requires: none
      * @returns: none
      */
     void onLongClick(View view, int position);
}
