package com.example.classattendancemobileapp;

import android.content.Context;
import android.widget.Toast;

import com.example.classattendancemobileapp.database.AppDatabase;
import com.example.classattendancemobileapp.database.Classes;
import com.example.classattendancemobileapp.database.Student;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

/**
 * Created by User on 21/02/2018.
 */

public class StudentController {
     private static final int ATTRIBCOUNT = 3;
     Context context; // variable holder for the running environment used

     public StudentController(Context context){
        this.context = context;
     }

     public boolean insertStudent(String className, String firstName, String lastName, String studentNumber){
          int id;

          id = getID(className);
          if(firstName.length() ==0 || lastName.length()==0 || studentNumber.length()==0) {
               Toast.makeText(context, "Fields must not be blank! ", Toast.LENGTH_SHORT).show();
               return false;
          }
          if(MainActivity.db.studentDao().countMatchStudent(id, firstName, lastName) > 0) {
               Toast.makeText(context, "Student is already in this class! ", Toast.LENGTH_SHORT).show();
               return false;
          }
          Student stud = new Student(id, studentNumber, firstName, lastName);
          MainActivity.db.studentDao().insert(stud);
          Toast.makeText(context, "Student is successfully added! ", Toast.LENGTH_SHORT).show();

          return true;
     }

     public int getID(String className){
          Classes listClass = MainActivity.db.classesDao().getByName(className);
          return  listClass.getClassID();
     }

     public List<Student> getAllStudents(String className){
          int classID;
          List<Student> studentList;
          classID = getID(className);
          studentList = MainActivity.db.studentDao().getByClassID(classID);
          return studentList;

     }

     public void insertMultipleStudents(String className, String filename){
          List<String[]> studentList = readFile(filename);
//          if(studentList != null){
//               for(int i=0; i<studentList.size(); i++){
//                    String studentNum = studentList.get(i)[0];
//                    String firstName = studentList.get(i)[1];
//                    String lastName = studentList.get(i)[2];
//                    Student newStudent = new Student(getID(className), studentNum, firstName, lastName);
//                    MainActivity.db.studentDao().insert(newStudent);
//               }
//               Toast.makeText(context, "Student is successfully added! ", Toast.LENGTH_SHORT).show();
//          }


     }

     public List<String[]> readFile(String filename){
//          if(filename.split(".")[filename.split(".").length-1] != "csv"){
//               Toast.makeText(context, "invalid format. Must be comma separated or do not have 3 attributes", Toast.LENGTH_SHORT).show();
//               return null;
//          }

          File myFile = new File(filename);
//          Toast.makeText(context, myFile.getPath(), Toast.LENGTH_SHORT).show();
          try{

               // BufferedReader dataInput = new BufferedReader(new InputStreamReader(getAssets().open("data.csv")));
               BufferedReader dataInput = new BufferedReader(new InputStreamReader(context.openFileInput(myFile.getAbsolutePath())));

               String line ="";
               //int lineNo=0;
               // line=dataInput.readLine();
               List<String[]> parsedStudents = new ArrayList<String[]>();;
               while((line=dataInput.readLine()) != null ){
                    //lineNo++;
                    String trimmedLine = line.trim(); //remove trailing whitespaces
                    try{
                         String[] studentAttrib = trimmedLine.split(",");
                         // Check if the line's num of attributes matches the attribute count (3)
                         if(studentAttrib.length != ATTRIBCOUNT){
                              // Line do not have 3 attribs or not comma separated
                              // Toast.makeText(context, "Error in line no "+ Integer.toString(lineNo), Toast.LENGTH_SHORT).show();
                              Toast.makeText(context, "invalid format. Must be comma separated or do not have 3 attributes", Toast.LENGTH_SHORT).show();
                              return null;
                         }

                         String[] parsedAttrib = new String[ATTRIBCOUNT];
                         // Check each attribute's validity
                         for (int i=0; i<ATTRIBCOUNT; i++) {
                              studentAttrib[i] = studentAttrib[i].trim();     //remove trailing whitespaces

                              // Check if the attribute's is not empty
                              if (studentAttrib[i].length() < 3) {
                                   // Log.d("Parse","Error in line no "+Integer.toString(lineNo));
                                   Toast.makeText(context, "empty attribute", Toast.LENGTH_SHORT).show();
                                   return null;
                              }

                              // Check if it is enclosed in quotation marks
                              if (studentAttrib[i].charAt(0) != '\"' || studentAttrib[i].charAt(studentAttrib[i].length() - 1) != '\"') {
                                   // Log.d("Parse","Error in line no "+Integer.toString(lineNo));
                                   Toast.makeText(context, "attributes are not enclosed in quote marks", Toast.LENGTH_SHORT).show();
                                   return null;
                              }
                              // Check if there are only the two quotation marks, no other quote marks allowed even if escaped
                              // method lifted from https://stackoverflow.com/questions/275944/java-how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
                              if (studentAttrib[i].length() - studentAttrib[i].replace("\"", "").length() != 2) {
                                   // Log.d("Parse","Error in line no "+Integer.toString(lineNo));
                                   Toast.makeText(context, "only enclosing quote marks are allowed", Toast.LENGTH_SHORT).show();
                                   return null;
                              }

                              parsedAttrib[i] = studentAttrib[i].replace("\"", "");
                              // Log.d("Parsed",parsedAttrib[i]);

                         }
                         parsedStudents.add(parsedAttrib);
                    }
                    catch(Exception e){
                         Toast.makeText(context, "Error, there is formatting error", Toast.LENGTH_SHORT).show();
                         return null;
                    }

               }
               return parsedStudents;

          }
          catch(Exception ex) {
               Toast.makeText(context, "Error opening the file", Toast.LENGTH_SHORT).show();
               return null;
          }

     }

}
