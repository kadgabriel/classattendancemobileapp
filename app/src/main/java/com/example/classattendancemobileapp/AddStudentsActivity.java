package com.example.classattendancemobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddStudentsActivity extends AppCompatActivity {

     RadioButton singleStudentRb, csvRb;
     TextView firstNameTv, lastNameTv, snoTv, pathTv;
     EditText firstNameEt, lastNameEt, snoEt, pathEt;
     Button pathButton, confirmAddStudentsButton;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_add_students);

          singleStudentRb = findViewById(R.id.singleStudentRb);
          csvRb = findViewById(R.id.csvRb);

          firstNameTv = findViewById(R.id.firstNameTv);
          lastNameTv = findViewById(R.id.lastNameTv);
          snoTv = findViewById(R.id.snoTv);
          pathTv = findViewById(R.id.pathTv);

          firstNameEt = findViewById(R.id.firstNameEt);
          lastNameEt = findViewById(R.id.lastNameEt);
          snoEt = findViewById(R.id.snoEt);
          pathEt = findViewById(R.id.pathEt);

          pathButton = findViewById(R.id.pathButton);
          confirmAddStudentsButton = findViewById(R.id.confirmAddStudentsButton);

          singleStudentRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                         csvRb.setChecked(false);
               }
          });

          csvRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                         singleStudentRb.setChecked(false);
               }
          });
     }
}
