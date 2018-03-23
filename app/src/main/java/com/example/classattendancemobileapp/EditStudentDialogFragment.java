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
 * @File:  EditStudentDialogFragment.java
 * @Creation Date: 20/03/18
 * @Version: 1.0
 */

package com.example.classattendancemobileapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.Student;

public class EditStudentDialogFragment extends DialogFragment {
     String[] studentInfo; // stores the current information about the student being edited and the new values
     EditStudentDialogListener listener; // the object that will listen for the confirmation or cancellation of the edit

     public interface EditStudentDialogListener{

          /**
           * onDialogPositiveClick() <20/03/2018>
           * - a callback function when the dialog receives a positive confirmation
           * @param: studentInfo - string array containing the information about the student being edited
           * @requires: none
           * @returns: none
           */
          void onDialogPositiveClick(String[] studentInfo);
     }

     /**
      * newInstance() <20/03/2018>
      * - an alternative constructor method for the DialogFragment
      * @param: studentInfo - string array containing the information about the student being edited
      * @requires: none
      * @returns: a new EditStudentDialogFrament object
      */
     public static EditStudentDialogFragment newInstance(String[] studentInfo){
          EditStudentDialogFragment dialogFragment = new EditStudentDialogFragment();

          Bundle args = new Bundle();
          args.putStringArray("STUDENT_INFO", studentInfo);
          dialogFragment.setArguments(args);

          return dialogFragment;
     }

     /**
      * onCreate() <20/03/2018>
      * - android function called when the DialogFragment is created
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: none
      */
     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          studentInfo = getArguments().getStringArray("STUDENT_INFO");
     }

     /**
      * onAttach() <20/03/2018>
      * - android function called when the DialogFragment is attached to its parent calling activity/fragment
      * @param: context - the current running environment
      * @requires: none
      * @returns: none
      */
     @Override
     public void onAttach(Context context) {
          super.onAttach(context);

          try {
               listener = (EditStudentDialogListener) context;
          }catch (ClassCastException e){
               throw new ClassCastException(context.toString() + " must implement DialogListener");
          }
     }

     /**
      * onCreateDialog() <20/03/2018>
      * - android function called which should contain the construction of the dialog
      * @param: savedInstanceState - the saved state of the activity in memory
      * @requires: none
      * @returns: a new Dialog object
      */
     @NonNull
     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          LayoutInflater inflater = getActivity().getLayoutInflater();
          View v = (inflater.inflate(R.layout.dialog_edit_student,null));
          final EditText firstNameEt = v.findViewById(R.id.firstNameEt); // EditText widget to be able to change the first name of the student
          final EditText lastNameEt = v.findViewById(R.id.lastNameEt); // EditText widget to be able to change the last name of the student
          final EditText snoEt = v.findViewById(R.id.snoEt); // EditText widget that displays the student number

          firstNameEt.setText(studentInfo[0]);
          lastNameEt.setText(studentInfo[1]);
          snoEt.setText(studentInfo[2]);
          snoEt.setEnabled(false);
          builder.setView(v)
                    .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                              String[] newInfo = {firstNameEt.getText().toString(), lastNameEt.getText().toString(), snoEt.getText().toString()};
                              listener.onDialogPositiveClick(newInfo);
                         }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                              //do nothing
                         }
                    })
                    .setTitle("Edit Student")
                    .setIcon(R.drawable.ic_edit_black_24dp);

          return builder.create();
     }
}
