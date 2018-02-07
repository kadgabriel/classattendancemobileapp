package com.example.classattendancemobileapp.database;

/**
 * Created by arielle on 2/6/18.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface StudentDao {

     @Query("SELECT * FROM student WHERE classID= :classID_x")
     List<Student> getAll(int classID_x);

     @Insert
     void insert(Student x);

     @Delete
     void delete(Student x);

     @Update
     void update(Student x);
}