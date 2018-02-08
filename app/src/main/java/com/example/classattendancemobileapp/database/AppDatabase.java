package com.example.classattendancemobileapp.database;
/*
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @Author(s): Arielle Gabriel
* @File:  AppDatabase.java
* @Version: 1.0
*
* */

/*
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.0 <04/02/2018> - Arielle Gabriel
*    - created initial file containing functions for classesDao() and studentDao()
* */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 1, entities = {Classes.class, Student.class, Attendance.class})
public abstract class AppDatabase extends RoomDatabase {
     /* include classesDao */
     public abstract ClassesDao classesDao();

     /* include studentDao */
     public abstract StudentDao studentDao();
}
