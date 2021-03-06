/**
* Class Attendance Mobile App
*
* “This is a course requirement for CS 192 Software Engineering II
* under the supervision of Asst. Prof. Ma. Rowena C. Solamo
* of the Department of Computer Science, College of Engineering,
* University of the Philippines, Diliman for the AY 2017-2018”.
*
* @File Author(s): Ronnel Austria, Arielle Gabriel, Oliver Atienza
*
* */

/**
 * Code History
 *   Version x.x <DD/MM/YYYY> - Author
 *        [description of changes]
 *
 *   Version 1.5 <09/04/2018> - Arielle Gabriel
 *        - modified add multiple students related functions to remove checking of quote marks and to building a string of errors
 *
 *   Version 1.4 <23/03/2018> - Oliver Atienza
 *        - added edit error detection (when either of the edit fields are empty)
 *
 *   Version 1.3 <21/03/2018> - John Oliver
 *        - added getStudent() and updateStudent()
 *
 *   Version 1.2 <06/03/2018> - Arielle Gabriel
 *        - added getStudentAttendance()
 *
 *   Version 1.1 <22/02/2018> - Arielle Gabriel
 *        - added insertMultipleStudents(), checkStudentsInDB(), readFile()
 *
 *   Version 1.0 <21/02/2018> - Ronnel Austria
 *        - created initial file for student controller
 *   */

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
* @Version: 1.4
*
* */

package com.example.classattendancemobileapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class StudentController {
     private static final int ATTRIBCOUNT = 3; // variable holder for number of student attributes
     Context context; // variable holder for the running environment used
     private StringBuilder parsingErrors; // variable holder for string containing the parsing errors

     /**
      * StudentController() <14/02/2018>
      * - the class constructor
      * @params: context - to identify the current running environment
      * @requires: none
      * @returns: none
      */
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
      * updateStudent() <20/03/2018>
      * - update a student's information in the database
      * @param: classID - ID of class, studentNumber - the student number of the student, updateInfo - contains the update information
      *         of student
      * @requires: none
      * @returns: boolean
      */
     public boolean updateStudent(int classID, String studentNumber, String[] updateInfo){
          if(updateInfo[0].length() == 0 || updateInfo[1].length() == 0)
               return false;
          Student student = MainActivity.db.studentDao().getStudent(classID, studentNumber);
          student.setFirstName(updateInfo[0]);
          student.setLastName(updateInfo[1]);
          student.setStudentNum(updateInfo[2]);

          MainActivity.db.studentDao().update(student);
          return true;
     }

     /**
     * getID() <21/02/2018>
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

     /**
      * getStudent() <20/03/2018>
      * - retrieves a single student from the database
      * @param: classID - the class ID to which the student belongs to, sno - the student number of the student
      * @requires: none
      * @returns: a Student object
      */
     public Student getStudent(int classID, String sno){
          return MainActivity.db.studentDao().getStudent(classID, sno);
     }

     /**
      * getStudentAttendance() <06/03/2018>
      * - counts the number of present, absent, late entries for a student
      * @param: classID - target class ID, sno - student number of the target student
      * @requires: attendance table
      * @returns: int[] - array of integers containing overview of student's attendance
      */
     public int[] getStudentAttendance(int classID, String sno){
          int[] studentRecord = new int[3]; //
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
          Boolean validCSV; // variable holder if CSV file is parsed correctly with no errors
          validCSV =  TRUE;

          if(filename != null){
               Log.d("mime", context.getContentResolver().getType(filename));
               MimeTypeMap mime = MimeTypeMap.getSingleton();
               if(mime.getExtensionFromMimeType(context.getContentResolver().getType(filename)) != "csv"){
                    Toast.makeText(context,"File must be a .csv file",Toast.LENGTH_LONG).show();
                    return;
               }

               List<String[]> studentList = readFile(filename);
               if(studentList != null){
                    boolean checkDuplicates; // true if there are duplicate student number in DB, false otherwise
                    for(int i=0;i<studentList.size(); i++){
                         checkDuplicates=checkStudentsInDB(getID(className),studentList.get(i)[0]);
                         if(checkDuplicates){
//                              Toast.makeText(context,"Student with student number "+studentList.get(i)[0]+" already in exists in class",Toast.LENGTH_LONG).show();
                              parsingErrors.append("- Student number ").append(studentList.get(i)[0]).append(" already exists in class\n");
                              validCSV = FALSE;
                         }
                    }
                    if(!validCSV){
                         Log.d("addStudents", parsingErrors.toString());
                         AddStudentsActivity.errorDialog(parsingErrors.toString());
                         return;
                    }

                    for(int i=0; i<studentList.size(); i++){
                         String studentNum = studentList.get(i)[0]; // holder for student number;
                         String firstName = studentList.get(i)[2]; // holder for first name
                         String lastName = studentList.get(i)[1]; // holder for last name
                         Student newStudent = new Student(getID(className), studentNum, firstName, lastName);
                         MainActivity.db.studentDao().insert(newStudent);
                    }
                    Toast.makeText(context, "Students are successfully added! ", Toast.LENGTH_LONG).show();
               }
          }
          else{
               Toast.makeText(context, "No file selected", Toast.LENGTH_LONG).show();
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
          Boolean parsingValid;  // variable holder for validity of parsing, true if no parsing error, false otherwise
          String line; // variable holder for string line in the file
          int lineNo=0; // variable holder for current line no. being read

          this.parsingErrors =  new StringBuilder();
          try{
               parsingValid = TRUE;
               InputStream is = context.getContentResolver().openInputStream(filename); // variable holder for input stream
               if(is == null){
                    Toast.makeText(context, "Unexpected error while opening the file. Please select the file again.", Toast.LENGTH_LONG).show();
                    return null;
               }

               BufferedReader dataInput = new BufferedReader(new InputStreamReader(is)); // variable holder for buffered reader
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
                              parsingErrors.append("- Line ").append(Integer.toString(lineNo)).append(": Missing an attribute\n");
                              parsingValid = FALSE;
                              continue;
                         }

                         String[] parsedAttrib = new String[ATTRIBCOUNT];
                         /* Check each attribute's validity */
                         for (int i=0; i<ATTRIBCOUNT; i++) {
                              studentAttrib[i] = studentAttrib[i].trim();     //remove trailing whitespaces

                              /* Check if the attribute is not empty */
                              if (studentAttrib[i].length() < 1) {
                                   parsingErrors.append("- Line ").append(Integer.toString(lineNo)).append(": Missing an attribute\n");
                                   parsingValid = FALSE;
                                   continue;
                              }

                              /* Removed/Commented checking quote marks */
                              /* Check if it is enclosed in quotation marks */
                              /*if (studentAttrib[i].charAt(0) != '\"' || studentAttrib[i].charAt(studentAttrib[i].length() - 1) != '\"') {
                                   Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+"\nContains attribute that is not enclosed in quote marks", Toast.LENGTH_SHORT).show();
                                   parsingValid = FALSE;
                              }*/
                              /* Check if there are only the two quotation marks, no other quote marks allowed even if escaped
                              * method lifted from https://stackoverflow.com/questions/275944/java-how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
                              */
                              /*if (studentAttrib[i].length() - studentAttrib[i].replace("\"", "").length() != 2) {
                                   Toast.makeText(context, "Error in file line no "+ Integer.toString(lineNo)+"\nOnly enclosing quote marks are allowed", Toast.LENGTH_SHORT).show();
                                   parsingValid = FALSE;
                              }*/

                              parsedAttrib[i] = studentAttrib[i].replace("\"", "");

                         }
                         parsedStudents.add(parsedAttrib);
                    }
                    catch(Exception e){
                         Log.d("addMultiStudent",e.getMessage());
                         parsingErrors.append("- Line no ").append(Integer.toString(lineNo)).append(": Improper format\n");
                         parsingValid = FALSE;
                    }
               }
               Log.d("addMultiStudent",parsingErrors.toString());
               if(parsingValid){
                    return parsedStudents;
               }
               else{
                    AddStudentsActivity.errorDialog(parsingErrors.toString());
                    return null;
               }

          }
          catch(Exception ex) {
               Log.d("addMultiStudent",ex.getMessage());
               Toast.makeText(context, "Unexpected error while opening the file.\n"+ex.getMessage(), Toast.LENGTH_LONG).show();
               return null;
          }



     }

}
