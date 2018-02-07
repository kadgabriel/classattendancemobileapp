package com.example.classattendancemobileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateClassActivity extends AppCompatActivity {
    Button createClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class_activity);
        createClassButton = (Button)findViewById(R.id.createClassButton);
        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Class successfully created!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
