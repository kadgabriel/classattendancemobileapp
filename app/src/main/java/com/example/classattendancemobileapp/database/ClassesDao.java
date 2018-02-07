package com.example.classattendancemobileapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by arielle on 2/5/18.
 */


@Dao
public interface ClassesDao {


     @Query("SELECT * FROM classes")
     List<Classes> getAll();

     @Query("SELECT * FROM classes LIMIT 1")
     Classes getOne();

     @Query("SELECT * FROM classes WHERE classID = :ID LIMIT 1")
     Classes getByID(int ID);

     @Query("SELECT count(*) FROM classes")
     int countAll();

     @Query("SELECT count(*) FROM classes WHERE className=:name")
     int countMatchName(String name);

     @Insert
     void insert(Classes singleClasses);

     @Delete
     void delete(Classes singleClass);

     @Update
     void update(Classes singleClass);
}