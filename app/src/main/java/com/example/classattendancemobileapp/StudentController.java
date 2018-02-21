package com.example.classattendancemobileapp;

import android.content.Context;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.AppDatabase;
import com.example.classattendancemobileapp.database.Classes;
import com.example.classattendancemobileapp.database.Student;

import java.util.List;

/**
 * Created by User on 21/02/2018.
 */

public class StudentController {

    Context context; // variable holder for the running environment used

    public StudentController(Context context){
        this.context = context;
    }

    public boolean insertStudent(String className, String firstName, String lastName, String studentNumber){
        int id;

        id = getID(className);
        if(firstName.length() ==0 || lastName.length()==0 || studentNumber.length()==0) {
            Toast.makeText(context, "Fields must not be blank! ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(MainActivity.db.studentDao().countMatchStudent(id, firstName, lastName) > 0) {
            Toast.makeText(context, "Student is already in this class! ", Toast.LENGTH_SHORT).show();
            return false;
        }
        Student stud = new Student(id, studentNumber, firstName, lastName);
        MainActivity.db.studentDao().insert(stud);
        Toast.makeText(context, "Student is successfully added! ", Toast.LENGTH_SHORT).show();

        return true;
    }

    public int getID(String className){
        Classes listClass = MainActivity.db.classesDao().getByName(className);
        return  listClass.getClassID();
    }

    public List<Student> getAllStudents(String className){
        int classID;
        List<Student> studentList;
        classID = getID(className);
        studentList = MainActivity.db.studentDao().getByClassID(classID);
        return studentList;

    }



}
