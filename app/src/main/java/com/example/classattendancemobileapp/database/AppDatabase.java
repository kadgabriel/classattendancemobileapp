package com.example.classattendancemobileapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by arielle on 2/5/18.
 */

@Database(version = 1, entities = {Classes.class, Student.class, Attendance.class})
public abstract class AppDatabase extends RoomDatabase {
     public abstract ClassesDao classesDao();
     public abstract StudentDao studentDao();
}
