package com.example.classattendancemobileapp;

import android.content.Context;
import android.view.View;
import android.arch.persistence.room.Room;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.*;
import java.util.List;
import java.lang.Object.*;

/**
 * Created by Oli on 2/7/2018.
 */

public class ClassController {
    AppDatabase db;
    Context context;

    public ClassController(AppDatabase db, Context context){
        this.db = db;
        this.context = context;
    }

    public boolean insertClass(String className, String classDesc){
        if(className.length() == 0){
            Toast.makeText(context, "Name must not be empty.", Toast.LENGTH_SHORT).show();
            return false;  //name is empty
        }
        if(db.classesDao().countMatchName(className) > 0) {
            Toast.makeText(context, "Duplicate name.", Toast.LENGTH_SHORT).show();
            return false;  //name has a match in db

        }
        db.classesDao().insert(new Classes(className, classDesc));
        Toast.makeText(context, "Successfully added a class " + className + ".", Toast.LENGTH_SHORT).show();
        return true;
    }

    public List<Classes> getAllClasses() {
        List<Classes> listClass = db.classesDao().getAll();

        return listClass;
    }
}
