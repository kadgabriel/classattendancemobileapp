package com.example.classattendancemobileapp.database;

/**
 * Created by arielle on 2/4/18.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
          tableName = "attendance",
          primaryKeys = {"classID", "studentNum", "date"},
          foreignKeys = {
                    @ForeignKey(entity = Classes.class,
                         parentColumns = "classID",
                         childColumns = "classID",
                         onDelete = CASCADE,
                         onUpdate = CASCADE),
                    }
)

class Attendance{
     @ColumnInfo(name = "classID")
     private int classID;

     @ColumnInfo(name = "studentNum")
     private String studentNum;

     @ColumnInfo(name = "date")
     private String date;

     @ColumnInfo(name = "entry")
     private String entry;

     public int getClassID(){
          return this.classID;
     }

     public String getStudentNum() {
          return this.studentNum;
     }
     public String getDate() {
          return this.date;
     }

     public String getEntry() {
          return this.entry;
     }

     public void setClassID(int classID){
          this.classID = classID;
     }

     public void setStudentNum(String studentNum) {
          this.studentNum = studentNum;
     }
     public void setDate(String date) {
          this.date = date;
     }

     public void setEntry(String entry) {
          this.entry = entry;
     }
}