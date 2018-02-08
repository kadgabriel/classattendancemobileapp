package com.example.classattendancemobileapp;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.AppDatabase;

public class CreateClassActivity extends AppCompatActivity {
    static AppDatabase db;
    ClassController classController;
    Button createClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class_activity);
        buildDB();
        classController = new ClassController(db, getApplicationContext());
        createClassButton = (Button)findViewById(R.id.createClassButton);
        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText classNameEditText =  findViewById(R.id.classNameEditText);
                EditText classDescEditText =  findViewById(R.id.classDescEditText);
                String className;
                String classDesc;
                className = classNameEditText.getText().toString();
                classDesc = classDescEditText.getText().toString();
                classController.insertClass(className, classDesc);

            }
        });
    }
    public void buildDB(){
//        db = Room.inMemoryDatabaseBuilder(
//                getApplicationContext(),
//                AppDatabase.class).allowMainThreadQueries().build();
                    db = Room.databaseBuilder(getApplicationContext(),
                              AppDatabase.class, "classattendance").allowMainThreadQueries().build();
    }
}
