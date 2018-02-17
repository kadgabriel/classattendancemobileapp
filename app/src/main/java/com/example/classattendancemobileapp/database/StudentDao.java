/*
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

/*
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.01 <17/02/2018> - Arielle Gabriel
*    - renamed function getAll() to getByClassID()
*
* Version 1.0 <06/02/2018> - Arielle Gabriel
*    - created initial file for students dao
* */

/*
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  StudentDao.java
* @Creation Date: 06/02/18
* @Version: 1.01
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
public interface StudentDao {

     /*
     * getByClassID() <06/02/2018>
     * - returns all students given a classID
     * @param: int ID - ID of the target class
     * @requires: none
     * @returns: List<Student> - list containing Students object of the matched ID
     * */
     @Query("SELECT * FROM student WHERE classID= :ID")
     List<Student> getByClassID(int ID);

     /*
     * insert() <06/02/2018>
     * - inserts a new student to the database
     * @param: stud - Student object containing the student to be inserted
     * @requires: none
     * @returns: none
     * */
     @Insert
     void insert(Student stud);

     /*
     * delete() <06/02/2018>
     * - deletes a student from database
     * @param: stud - Student object containing the student to be deleted
     * @requires: none
     * @returns: none
     * */
     @Delete
     void delete(Student stud);

     /*
     * update() <06/02/2018>
     * - updates a student entity from database
     * @param: stud - Student object containing the student to be updated
     * @requires: none
     * @returns: none
     * */
     @Update
     void update(Student stud);
}