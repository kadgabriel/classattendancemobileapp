/**
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @File Author(s): Arielle Gabriel
*
* */

/**
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.2 <06/03/2018> - Arielle Gabriel
*    - updated comments in functions
*
* Version 1.1 <17/02/2018> - Arielle Gabriel
*    - added getByName() function
*
* Version 1.0 <05/02/2018> - Arielle Gabriel
*    - created initial file for classes dao
*
* */

/**
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  ClassesDao.java
* @Creation Date: 05/02/18
* @Version: 1.2
*
* */

package com.example.classattendancemobileapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ClassesDao {

     /**
     * getAll() <05/02/2018>
     * - returns all classes in the DB
     * @param: none
     * @requires: classes table
     * @returns: List<Classes> - list containing Classes objects
     * */
     @Query("SELECT * FROM classes")
     List<Classes> getAll();

     /**
     * getOne() <05/02/2018>
     * - returns one class from the DB
     * @param: none
     * @requires: classes table
     * @returns: Classes - one Classes object from the DB
     * */
     @Query("SELECT * FROM classes LIMIT 1")
     Classes getOne();

     /**
     * getByID() <05/02/2018>
     * - returns class given a classID
     * @param: int - target classID
     * @requires: classes table
     * @returns: Classes - object containing the matched classID
     * */
     @Query("SELECT * FROM classes WHERE classID = :ID LIMIT 1")
     Classes getByID(int ID);

     /**
     * getByName() <17/02/2018>
     * - returns class given a class name
     * @param: String - target class's name
     * @requires: classes table
     * @returns: Classes - object containing the matched classID
     * */
     @Query("SELECT * FROM classes WHERE className = :name LIMIT 1")
     Classes getByName(String name);

     /**
     * countAll() <05/02/2018>
     * - returns the number of classes
     * @param: none
     * @requires: classes table
     * @returns: int - count of classes
     * */
     @Query("SELECT count(*) FROM classes")
     int countAll();

     /**
     * countMatchName() <05/02/2018>
     * - returns an int
     * @param: name - name of target class
     * @requires: classes table
     * @returns: int - count of matching class name
     * */
     @Query("SELECT count(*) FROM classes WHERE className=:name")
     int countMatchName(String name);

     /**
     * insert() <05/02/2018>
     * - inserts a new class to the database
     * @param: singleClasses - object containing the class to be inserted
     * @requires: classes table
     * @returns: none
     * */
     @Insert
     void insert(Classes singleClasses);

     /**
     * delete() <05/02/2018>
     * - deletes a given class from the database
     * @param: singleClasses - object containing the class to be deleted
     * @requires: classes table
     * @returns: none
     * */
     @Delete
     void delete(Classes singleClass);

     /**
     * update() <05/02/2018>
     * - updates a class already existing the database
     * @param: singleClasses - object containing the class to be updated
     * @requires: classes table
     * @returns: none
     * */
     @Update
     void update(Classes singleClass);
}