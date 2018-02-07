package com.example.classattendancemobileapp.database;
//class Classes {
//}

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "classes")
public class Classes{
     @PrimaryKey(autoGenerate = true)
     @ColumnInfo(name = "classID")
     private int classID;

     @ColumnInfo(name = "className")
     private String className;
     @ColumnInfo(name = "classDesc")
     private String classDesc;

     Classes(){}
     public Classes(String name, String desc){
          this.className = name;
          this.classDesc = desc;
     }

     public void setClassID(int classID){
          this.classID = classID;
     }

     public void setClassName(String className){
          this.className = className;
     }

     public void setClassDesc(String classDesc){
          this.classDesc = classDesc;
     }

     public int getClassID(){
          return this.classID;
     }

     public String getClassName(){
         return this.className;
     }

     public String getClassDesc(){
         return this.classDesc;
     }

}