/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): Arielle
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.0 <09/04/2018> - Arielle Gabriel
 *        - created initial file based on EditStudentDialogFragment
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  EditStudentDialogFragment.java
 * @Creation Date: 09/04/18
 * @Version: 1.0
 */


package com.example.classattendancemobileapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class AddStudentErrorDialog extends DialogFragment {
     String errors; // variable holder for string of errors to be displayed

     /**
     * newInstance() <09/04/2018>
     * - an alternative constructor method for the DialogFragment
     * @param: errors - string containing the errors to be displayed
     * @requires: none
     * @returns: a new AddStudentErrorDialog object
     */
     public static AddStudentErrorDialog newInstance(String errors){
          AddStudentErrorDialog dialogFragment = new AddStudentErrorDialog();

          Bundle args = new Bundle();
          args.putString("CSV_ERROR", errors);
          dialogFragment.setArguments(args);

          return dialogFragment;
     }

     /**
     * onCreate() <09/04/2018>
     * - function called during creation of class
     * @param: savedInstanceState - the saved state of the activity in memory
     * @requires: none
     * @returns: none
     */
     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          errors = getArguments().getString("CSV_ERROR");
     }

     /**
     * onAttach() <09/04/2018>
     * - android function called when the DialogFragment is attached to its parent calling activity/fragment
     * @param: context - the current running environment
     * @requires: none
     * @returns: none
     */
     @Override
     public void onAttach(Context context) {
          super.onAttach(context);
     }

     /**
      * onCreateDialog() <09/04/2018>
      * - android function called which should contain the construction of the dialog
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: a new Dialog object
      */
     @NonNull
     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          builder.setTitle("Errors detected");
          builder.setMessage(this.errors);
          builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                    Log.d("addStudents", "OK");
               }
          });
          return builder.create();
     }
}
