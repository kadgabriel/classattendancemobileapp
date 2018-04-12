/**
 * Class Attendance Mobile App
 *
 * This is a course requirement for CS 192 Software Engineering II
 * under the supervision of Asst. Prof. Ma. Rowena C. Solamo
 * of the Department of Computer Science, College of Engineering,
 * University of the Philippines, Diliman for the AY 2017-2018.
 *
 * @File Author(s): John Oliver, Ronnel Austria
 *
 */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.3 <20/03/2018> - Ronnel Austria
 *        - added editClass() function
 *
 *   Version 1.2 <09/02/2018> - John Oliver
 *        - added getByName() function and code comments
 *
 *   Version 1.1 <09/02/2018> - John Oliver
 *        - removed unnecessary imports
 *
 *   Version 1.0 <07/02/2018> - John Oliver
 *        - created initial file
 */

/**
 * Class Attendance Mobile App
 * Class Attendance Mobile App is a mobile application that allows the teacher to record
 * the attendance​ of the students​ digitally​ using a smart phone
 *
 * @Group members: Atienza, Austria, Gabriel
 * @Client: Asst. Prof. Ma. Rowena C. Solamo
 * @File:  ClassController.java
 * @Creation Date: 07/02/18
 * @Version: 1.3
 */

package com.example.classattendancemobileapp;

import android.content.Context;
import android.widget.Toast;
import com.example.classattendancemobileapp.database.*;
import java.util.List;

public class ClassController {

     AppDatabase db; // variable holder for the application's main database
     Context context; // variable holder for the running environment used

     /**
      * ClassController() <07/02/2018>
      * - the class constructor
      * @param: db - to store a reference to the applications database
      *         context - to identify the current running environment
      * @requires: none
      * @returns: none
      */
     public ClassController(AppDatabase db, Context context){
          this.db = db;
          this.context = context;
     }

     /**
      * insertClass() <07/02/2018>
      * - inserts a class directly into the database
      * @param: className - the name of the class to be inserted
      *         classDesc - a short description of the class to be inserted
      * @requires: none
      * @returns: boolean
      */
     public boolean insertClass(String className, String classDesc){
          if(className.length() == 0){
               Toast.makeText(context, "Name must not be empty.", Toast.LENGTH_SHORT).show();
               return false;  //name is empty
          }
          if(db.classesDao().countMatchName(className) > 0) {
               Toast.makeText(context, "Duplicate name.", Toast.LENGTH_SHORT).show();
               return false;  //name has a match in db
          }
          db.classesDao().insert(new Classes(className, classDesc));
          Toast.makeText(context, "Successfully added a class " + className + ".", Toast.LENGTH_SHORT).show();

          return true;
     }

      /**
      * editClass() <20/03/2018>
      * - edits a class already existing in the database
      * @param: oldClassName - the name of the class to be updated
      *         newClassName - the new name of the class
      *         oldClassDesc - a short description of the class to be updated
      *         newClassDesc - a new short description of the class
      * @requires: none
      * @returns: boolean
      */
     public boolean editClass(String oldClassName, String newClassName, String oldClassDesc, String newClassDesc){
          if(newClassName.length() == 0){
               Toast.makeText(context, "Name must not be empty.", Toast.LENGTH_SHORT).show();
               return false;  //name is empty
          }
          if(oldClassName.equals(newClassName) && oldClassDesc.equals(newClassDesc)){
               Toast.makeText(context, "No changes are made. Class info is unchanged.", Toast.LENGTH_SHORT).show();
               return true; //no changes are made
          }
          if(db.classesDao().countMatchName(newClassName) > 0 && !oldClassName.equals(newClassName)) {
               Toast.makeText(context, "Class already exists.", Toast.LENGTH_SHORT).show();
               return false;  //name has a match in db
          }

          Classes classObj; // variable holder for Classes object of the class to be updated
          classObj = getByName(oldClassName);
          classObj.setClassName(newClassName);
          classObj.setClassDesc(newClassDesc);
          MainActivity.db.classesDao().update(classObj);

          Toast.makeText(context, "Successfully updated class info.", Toast.LENGTH_SHORT).show();
          return true; // successful operation
     }

     public boolean deleteClass(String className){
          Classes classObj = getByName(className);
          MainActivity.db.classesDao().delete(classObj);
          return true;
     }
     /**
      * getAllClasses() <07/02/2018>
      * - gets all the classes in the database
      * @param: none
      * @requires: none
      * @returns: a java.util.List of Classes objects
      */
     public List<Classes> getAllClasses() {
          List<Classes> listClass = db.classesDao().getAll();

          return listClass;
     }

     /**
      * getByName() <25/02/2018>
      * - gets a class from the database
      * @param: name - name of the class to be retrived
      * @requires: none
      * @returns: a Classes object
      */
     public Classes getByName(String name){
          return db.classesDao().getByName(name);
     }
}
