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
 * @File:  RecyclerViewOnTouchListener.java
 * @Creation Date: 20/03/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewOnTouchListener implements RecyclerView.OnItemTouchListener {
     private ClickListener clickListener; // the ClickListener object which implement the same interface
     private GestureDetector gestureDetector; // android utility function for detecting touch gestures on the screen

     /**
      * RecyclerViewOnTouchListener() <20/03/2018>
      * - the class constructor
      * @param: context - the application's current running environment, recyclerView - a reference to the recyclerView widget to where this object is being attached to, clickListener - the object that will listen for touch events
      * @requires: none
      * @returns: a new RecyclerViewOnTouchListener instance
      */
     public RecyclerViewOnTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
          this.clickListener = clickListener;
          gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

               /**
                * onSingleTapUp() <20/03/2018>
                * - android function called once the gesture detector detects a tap release
                * @param: e - holds data about the captured event
                * @requires: none
                * @returns: boolean
                */
               @Override
               public boolean onSingleTapUp(MotionEvent e) {
                    return true;
               }

               /**
                * onLongPress() <20/03/2018>
                * - android function called once the gesture detector detects long press on an item
                * @param: e - holds data about the captured event
                * @requires: none
                * @returns: none
                */
               @Override
               public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(child != null && clickListener != null){
                         clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
               }
          });
     }

     /**
      * onInterceptTouchEvent() <20/03/2018>
      * - android function called once the gesture detector detects a tap on the screen
      * @param: rv - the recycler view the was touched/tapped, e - hold data about the captured event
      * @requires: none
      * @returns: boolean
      */
     @Override
     public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
          View child = rv.findChildViewUnder(e.getX(),e.getY());
          if(child != null && clickListener != null && gestureDetector.onTouchEvent(e))
               clickListener.onClick(child, rv.getChildAdapterPosition(child));
          return false;
     }

     /**
      * onTouchEvent() <20/03/2018>
      * - android function called once the gesture detector detects a tap on the screen
      * @param: rv - the recycler view the was touched/tapped, e - hold data about the captured event
      * @requires: none
      * @returns: none
      */
     @Override
     public void onTouchEvent(RecyclerView rv, MotionEvent e) {

     }

     /**
      * onRequestDisallowInterceptTouchEvent() <20/03/2018>
      * - may be called to all or disallow the onInterceptTouchEvent function
      * @param: disallowIntercept - true or false
      * @requires: none
      * @returns: none
      */
     @Override
     public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

     }
}
