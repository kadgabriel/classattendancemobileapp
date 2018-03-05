package com.example.classattendancemobileapp;

/**
 * Created by Oli on 2/25/2018.
 */

public class StudentListItem {

     String name;
     String sno;

     public StudentListItem(String name, String sno) {
          this.name = name;
          this.sno = sno;
     }

     public String getName() {
          return name;
     }

     public String getSno() {
          return sno;
     }
}
