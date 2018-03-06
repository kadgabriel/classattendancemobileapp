/**
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @File Author(s): Ronnel Austria, Arielle Gabriel
*
* */

/**
* Code History
*    Version x.x <DD/MM/YYYY> - Author
*         [description of changes]
*
* Version 1.2 <06/03/2018> - Arielle Gabriel
*    - added getStudentAttendance()
*
* Version 1.1 <22/02/2018> - Arielle Gabriel
*    - added insertMultipleStudents(), checkStudentsInDB(), readFile()
*
* Version 1.0 <21/02/2018> - Ronnel Austria
*    - created initial file for student controller
* */

/**
* Class Attendance Mobile App
*
* Class Attendance Mobile App is a mobile application that allows the teacher to record
* the attendance​ of the students​ digitally​ using a smart phone
*
* @Group members: Atienza, Austria, Gabriel
* @Client: Asst. Prof. Ma. Rowena C. Solamo
* @File:  StudentController.java
* @Creation Date: 21/02/18
* @Version: 1.2
*
* */

package com.example.classattendancemobileapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.AppDatabase;
import com.example.classattendancemobileapp.database.Classes;
import com.example.classattendancemobileapp.database.Student;
import com.example.classattendancemobileapp.database.Attendance;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21/02/2018.
 */

public class StudentController {
     private static final int ATTRIBCOUNT = 3; // variable holder for number of student attributes
     Context context; // variable holder for the running environment used

     public StudentController(Context context){
        this.context = context;
     }

     /**
     * insertStudent() <21/02/2018>
     * - insert a single students controller
     * @param: className - name of class, firstName - first name of student, lastName - last name of student, studentNumber - student number
     *         of student
     * @requires: none
     * @returns: none
     */

     public boolean insertStudent(String className, String firstName, String lastName, String studentNumber){
          int id;  //integer variable holder for classID

          id = getID(className);
          if(firstName.length() ==0 || lastName.length()==0 || studentNumber.length()==0) {
               Toast.makeText(context, "Fields must not be blank! ", Toast.LENGTH_SHORT).show();
               return false;
          }
          if(checkStudentsInDB(id, studentNumber)) {
               Toast.makeText(context, "Student is already in this class! ", Toast.LENGTH_SHORT).show();
               return false;
          }
          Student stud = new Student(id, studentNumber, firstName, lastName);
          MainActivity.db.studentDao().insert(stud);
          Toast.makeText(context, "Student is successfully added! ", Toast.LENGTH_SHORT).show();

          return true;
     }

     /**
     * insertMultipleStudents() <21/02/2018>
     * - get classID of a class name controller
     * @param: className - name of class, filename - Uri object containing the file
     * @requires: none
     * @returns: integer value of classID of the className
     */

     public int getID(String className){
          Classes listClass = MainActivity.db.classesDao().getByName(className);
          return  listClass.getClassID();
     }
     /**
      * getAllClasses() <07/02/2018>
      * - gets all the students from the given class in the database
      * @param: className - name of the class to retrieve the students from
      * @requires: none
      * @returns: a java.util.List of Students objects
      */
     public List<Student> getAllStudents(String className){
          int classID;
          List<Student> studentList;
          classID = getID(className);
          studentList = MainActivity.db.studentDao().getByClassID(classID);
          return studentList;

     }

     public int[] getStudentAttendance(int classID, String sno){
          int[] studentRecord = new int[3];
          studentRecord[0] = MainActivity.db.attendanceDao().countPresent(classID,sno);
          studentRecord[1] = MainActivity.db.attendanceDao().countLate(classID,sno);
          studentRecord[2] = MainActivity.db.attendanceDao().countAbsent(classID,sno);
          return  studentRecord;
     }

     /**
      * insertMultipleStudents() <22/02/2018>
      * - insert multiple students controller
      * @param: className - name of class, filename - Uri object containing the file
      * @requires: none
      * @returns: none
      */
     public void insertMultipleStudents(String className, Uri filename){
          Log.d("mime", context.getContentResolver().getType(filename));
          if(filename != null){
               MimeTypeMap mime = MimeTypeMap.getSingleton();
               if(mime.getExtensionFromMimeType(context.getContentResolver().getType(filename)) != "csv"){
                    Toast.makeText(context,"File must be a .csv file",Toast.LENGTH_SHORT).show();
                    return;
               }

               List<String[]> studentList = readFile(filename);
               if(studentList != null){
                    boolean checkDuplicates;
                    for(int i=0;i<studentList.size(); i++){
                         checkDuplicates=checkStudentsInDB(getID(className),studentList.get(i)[0]);
                         if(checkDuplicates){
                              Toast.makeText(context,"Student with student number "+studentList.get(i)[0]+" already in exists in class",Toast.LENGTH_LONG).show();
                              return;
                         }
                    }

                    for(int i=0; i<studentList.size(); i++){
                         String studentNum = studentList.get(i)[0]; // holder for student number;
                         String firstName = studentList.get(i)[2]; // holder for first name
                         String lastName = studentList.get(i)[1]; // holder for last name
                         Student newStudent = new Student(getID(className), studentNum, firstName, lastName);
                         MainActivity.db.studentDao().insert(newStudent);
                    }
                    Toast.makeText(context, "Students are successfully added! ", Toast.LENGTH_SHORT).show();
               }
          }
          else{
               Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show();
               return;
          }
     }

     /**
      * checkStudentsInDB() <22/02/2018>
      * - checks if the student number and ID is already existing in the DB
      * @param: classID - ID of class, studentNum - target student number
      * @requires: student table, StudentDao
      * @returns: true - if existing, false - otherwise
      */
     public boolean checkStudentsInDB(int classID, String studentNum){
          if(MainActivity.db.studentDao().countMatchStudentNum(classID,studentNum) == 0){
               return false;
          }
          return true;

     }

     /**
      * readFile() <22/02/2018>
      * - validates the content of the csv file
      * @param: filename - Uri object containing the csv file
      * @requires: none
      * @returns: List<String[]> of students if parsing is successful, null - if complications are found
      */
     public List<String[]> readFile(Uri filename){
          try{

               InputStream is = context.getContentResolver().openInputStream(filename);
               BufferedReader dataInput = new BufferedReader(new InputStreamReader(is));

               String line =""; // variable holder for string line in the file
               int lineNo=0; // variable holder for current line no. being read
               List<String[]> parsedStudents = new ArrayList<String[]>(); //variable holder for successfully parsed students
               while((line=dataInput.readLine()) != null ){
                    lineNo++;
                    /* remove trailing whitespaces */
                    String trimmedLine = line.trim(); // variable holder for trimmed line
                    try{
                         String[] studentAttrib = trimmedLine.split(","); // variable holder for individual student's attributes
                         /* Check if the line's num of attributes matches the attribute count (3) */
                         if(studentAttrib.length != ATTRIBCOUNT){
                              /* Line do not have 3 attribs or not comma separated */
                              Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+"\nMust be comma separated or does not have 3 attributes", Toast.LENGTH_SHORT).show();
                              return null;
                         }

                         String[] parsedAttrib = new String[ATTRIBCOUNT];
                         /* Check each attribute's validity */
                         for (int i=0; i<ATTRIBCOUNT; i++) {
                              studentAttrib[i] = studentAttrib[i].trim();     //remove trailing whitespaces

                              /* Check if the attribute's is not empty */
                              if (studentAttrib[i].length() < 3) {
                                   Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+"\nContains empty attribute", Toast.LENGTH_SHORT).show();
                                   return null;
                              }

                              /* Check if it is enclosed in quotation marks */
                              if (studentAttrib[i].charAt(0) != '\"' || studentAttrib[i].charAt(studentAttrib[i].length() - 1) != '\"') {
                                   Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+"\nContains attribute that is not enclosed in quote marks", Toast.LENGTH_SHORT).show();
                                   return null;
                              }
                              /* Check if there are only the two quotation marks, no other quote marks allowed even if escaped
                              * method lifted from https://stackoverflow.com/questions/275944/java-how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
                              */
                              if (studentAttrib[i].length() - studentAttrib[i].replace("\"", "").length() != 2) {
                                   Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+"\nOnly enclosing quote marks are allowed", Toast.LENGTH_SHORT).show();
                                   return null;
                              }

                              parsedAttrib[i] = studentAttrib[i].replace("\"", "");

                         }
                         parsedStudents.add(parsedAttrib);
                    }
                    catch(Exception e){
                         Log.d("addMultiStudent",e.getMessage());
                         Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+". Improper format.", Toast.LENGTH_SHORT).show();
                         return null;
                    }
               }
               return parsedStudents;

          }
          catch(Exception ex) {
               Log.d("addMultiStudent",ex.getMessage());
               Toast.makeText(context, "Error opening the file", Toast.LENGTH_SHORT).show();
               return null;
          }

     }

}
