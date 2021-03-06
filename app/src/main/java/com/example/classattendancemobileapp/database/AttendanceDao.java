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
* Version 1.1 <15/03/2018> - Arielle Gabriel
*    - added listDates, getbyDate, and getbyDateandStud
*
* Version 1.0 <06/03/2018> - Arielle Gabriel
*    - created initial file for attendance dao
* */

/*
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  AttendanceDao.java
* @Creation Date: 06/03/18
* @Version: 1.0
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
public interface AttendanceDao {

     /*
     * listDates() <15/03/2018>
     * - list of dates with attendance entries
     * @param: classID - target class
     * @requires: attendance table
     * @returns: string - list of distinct dates
     * */
     @Query("SELECT DISTINCT date FROM attendance WHERE classID=:classID")
     List<String> listDates(int classID);

     /*
     * getbyDate() <15/03/2018>
     * - gets the entries for a classID and date
     * @param: classID - target class, date - target date
     * @requires: attendance table
     * @returns: string - entries for all students given date and classID
     * */
     @Query("SELECT * FROM attendance WHERE classID=:classID and date=:date")
     List<Attendance> getbyDate(int classID, String date);

     /*
     * getbyDateandStud() <15/03/2018>
     * - gets the entry for a given student, classID and date
     * @param: classID - target class, studentNum - student number, date - target date
     * @requires: attendance table
     * @returns: string - entry for that date given student and classID
     * */
     @Query("SELECT entry FROM attendance WHERE classID=:classID and date=:date and studentNum=:sno LIMIT 1")
     String getbyDateandStud(int classID, String date, String sno);

     /*
     * countPresent() <06/03/2018>
     * - counts the number of "present" entries that the student have
     * @param: classID - target class, studentNum - student number
     * @requires: attendance table
     * @returns: int - count of entries in DB
     * */
     @Query("SELECT count(*) FROM attendance WHERE classID=:classID and studentNum=:studentNum and entry=\"P\"")
     int countPresent(int classID, String studentNum);

     /*
     * countLate() <06/03/2018>
     * - counts the number of "late" entries that the student have
     * @param: classID - target class, studentNum - student number
     * @requires: attendance table
     * @returns: int - count of entries in DB
     * */
     @Query("SELECT count(*) FROM attendance WHERE classID=:classID and studentNum=:studentNum and entry=\"L\"")
     int countLate(int classID, String studentNum);

     /*
     * countAbsent() <06/03/2018>
     * - counts the number of "absent" entries that the student have
     * @param: classID - target class, studentNum - student number
     * @requires: attendance table
     * @returns: int - count of entries in DB
     * */
     @Query("SELECT count(*) FROM attendance WHERE classID=:classID and studentNum=:studentNum and entry=\"A\"")
     int countAbsent(int classID, String studentNum);

     /*
     * countMatchAttendance() <06/03/2018>
     * - counts the number matching tuple of classID, student no, and date in the DB
     * @param: classID - target class, studentNum - student number, date - date
     * @requires: attendance table
     * @returns: int - count of matching entries in DB
     * */
     @Query("SELECT count(*) FROM attendance WHERE classID=:classID and studentNum=:studentNum and date=:date")
     int countMatchAttendance(int classID, String studentNum, String date);

     /*
     * insertMultipleAttendance() <06/03/2018>
     * - inserts multiple attendance entry to the database
     * @param: attendance - list of Attendance objects containing the entries to be inserted
     * @requires: attendance table
     * @returns: none
     * */
     @Insert
     void insertMultipleAttendance(List<Attendance> attendance);

     /*
     * insert() <06/03/2018>
     * - inserts a new attendance entry to the database
     * @param: attendance - Attendance object containing the entry to be inserted
     * @requires: attendance table
     * @returns: none
     * */
     @Insert
     void insert(Attendance attendance);

     /*
     * delete() <06/03/2018>
     * - deletes an attendance entry from database
     * @param: attendance - Attendance object containing the entry to be deleted
     * @requires: attendance table
     * @returns: none
     * */
     @Delete
     void delete(Attendance attendance);

     /*
     * update() <06/03/2018>
     * - updates an attendance entry entity from database
     * @param: attendance - Attendance object containing the entry to be updated
     * @requires: attendance table
     * @returns: none
     * */
     @Update
     void update(Attendance attendance);
}
