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

/**
 * Created by Oli on 3/20/2018.
 */

public class EditStudentDialogFragment extends DialogFragment {
     String[] studentInfo;
     EditStudentDialogListener listener;

     public interface EditStudentDialogListener{
          void onDialogPositiveClick(String[] studentInfo);
     }

     public static EditStudentDialogFragment newInstance(String[] studentInfo){
          EditStudentDialogFragment dialogFragment = new EditStudentDialogFragment();

          Bundle args = new Bundle();
          args.putStringArray("STUDENT_INFO", studentInfo);
          dialogFragment.setArguments(args);

          return dialogFragment;
     }

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          studentInfo = getArguments().getStringArray("STUDENT_INFO");
     }

     @Override
     public void onAttach(Context context) {
          super.onAttach(context);

          try {
               listener = (EditStudentDialogListener) context;
          }catch (ClassCastException e){
               throw new ClassCastException(context.toString() + " must implement DialogListener");
          }
     }

     @NonNull
     @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          LayoutInflater inflater = getActivity().getLayoutInflater();
          View v = (inflater.inflate(R.layout.dialog_edit_student,null));
          final EditText firstNameEt = v.findViewById(R.id.firstNameEt);
          final EditText lastNameEt = v.findViewById(R.id.lastNameEt);
          final EditText snoEt = v.findViewById(R.id.snoEt);

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
