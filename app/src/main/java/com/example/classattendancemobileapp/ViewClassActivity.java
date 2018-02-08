package com.example.classattendancemobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ViewClassActivity extends AppCompatActivity {

    ListView studentListView;
    TextView noStudentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_class_activity);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("CLASS_NAME"));

        studentListView = (ListView)findViewById(R.id.studentListView);
        noStudentTextView = (TextView)findViewById(R.id.noStudentTextView);
        String[] studentNames = new String[0];
        if(studentNames.length == 0)
            noStudentTextView.setVisibility(View.VISIBLE);
    }
}
