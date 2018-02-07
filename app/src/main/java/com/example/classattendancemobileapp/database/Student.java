package com.example.classattendancemobileapp.database;

/**
 * Created by arielle on 2/4/18.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
          tableName = "student",
          primaryKeys = {"classID", "studentNum"},
          foreignKeys = @ForeignKey(entity = Classes.class,
                    parentColumns = "classID",
                    childColumns = "classID",
                    onDelete=CASCADE,
                    onUpdate=CASCADE)
	     )
class Student {
//     @Index(name="index1", value="studentNum", unique = false)
     @ColumnInfo(name = "classID")
     private int classID;

     @ColumnInfo(name = "studentNum")
     private String studentNum;
     @ColumnInfo(name = "firstName")
     private String firstName;
     @ColumnInfo(name = "lastName")
     private String lastName;

     public int getClassID(){
          return this.classID;
     }

     public String getStudentNum(){
          return this.studentNum;
     }

     public String getFirstName(){
          return this.firstName;
     }

     public String getLastName(){
          return this.lastName;
     }

     public void setClassID(int classID){
          this.classID = classID;
     }

     public void setStudentNum(String studentNum){
          this.studentNum = studentNum;
     }

     public void setFirstName(String firstName){
          this.firstName = firstName;
     }

     public void setLastName(String lastName){
          this.lastName = lastName;
     }

     public String getName(){
          return this.lastName+", "+this.firstName;
     }


}