package com.example.classattendancemobileapp;

/**
 * Created by Oli on 2/25/2018.
 */

public class ClassListItem {

     String name;
     String desc;

     public ClassListItem(String name, String desc) {
          this.name = name;
          this.desc = desc;
     }

     public String getName() {
          return name;
     }

     public String getDesc() {
          return desc;
     }
}
