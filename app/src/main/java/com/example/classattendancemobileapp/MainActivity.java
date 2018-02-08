package com.example.classattendancemobileapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.classattendancemobileapp.database.AppDatabase;
import com.example.classattendancemobileapp.database.Classes;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static AppDatabase db;
    ListView classListView;
    FloatingActionButton addClassFAB;
    ClassController classController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildDB();

        classListView = (ListView)findViewById(R.id.classListView);
        addClassFAB = (FloatingActionButton) findViewById(R.id.addClassFAB);

        classController = new ClassController(db, getApplicationContext());
        classController.insertClass("", "computerscience192");
        classController.insertClass("CS193", "computerscience192");
        classController.insertClass("CS194", "computerscience192");
        classController.insertClass("CS195", "computerscience192");
        classController.insertClass("CS196", "computerscience192");
        classController.insertClass("CS197", "computerscience192");
        classController.insertClass("CS198", "computerscience192");



    }

    @Override
    public void onResume(){
        super.onResume();


        //Get info of classes from the database

        List<Classes> classList = classController.getAllClasses();
        final String[] classNames = new String[classList.size()];
        for(int i = 0; i < classList.size(); i++){
            classNames[i] = classList.get(i).getClassName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, classNames);

        classListView.setAdapter(adapter);


        addClassFAB = (FloatingActionButton) findViewById(R.id.addClassFAB);

        //Added listview item click listener
        classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ViewClassActivity.class);
                intent.putExtra("CLASS_NAME", classNames[(int) l]);
                startActivity(intent);
            }
        });


        addClassFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateClassActivity.class);
                startActivity(intent);
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
