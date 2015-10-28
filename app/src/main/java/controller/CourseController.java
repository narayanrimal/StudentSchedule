package controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.CourseTable;

/**
 * Created by Dara on 10/18/2015.
 */
public class CourseController {
    DatabaseAdapter databaseAdapter;

    public CourseController(Context context) {
        databaseAdapter = new DatabaseAdapter(context);
    }

    public CourseTable getCourseByDepartmentAndCourseNumber(String department, String course_number){
        CourseTable courseTable = new CourseTable();
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT * FROM course WHERE course_department='" + department + "' and course_number =" + course_number ;
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                courseTable.id = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
                courseTable.course_department = cursor.getString(cursor.getColumnIndex("course_department"));
                courseTable.course_number = cursor.getString(cursor.getColumnIndex("course_number"));
                courseTable.course_description =cursor.getString(cursor.getColumnIndex("course_description"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return courseTable;
    }

    public ArrayList<String> getDepartment(){
        ArrayList<String> departments = new ArrayList<>();
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT DISTINCT course_department FROM course";
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                departments.add(cursor.getString(cursor.getColumnIndex("course_department")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return departments;
    }

    public ArrayList<String> getCouserNumber(String department){
        ArrayList<String> courseNumbers = new ArrayList<>();
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT DISTINCT course_number FROM course where course_department ='" + department + "'";
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                courseNumbers.add(cursor.getString(cursor.getColumnIndex("course_number")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return courseNumbers;
    }

    public long getCourseID(String department, String course_number){
        long id = 0;
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT id FROM course where course_department ='" + department + "' and course_number=" + course_number;
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                id = cursor.getLong(cursor.getColumnIndex("id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<String> getCouserNumber2(){
        ArrayList<String> courseNumbers = new ArrayList<>();
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT DISTINCT course_number FROM course";
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                courseNumbers.add(cursor.getString(cursor.getColumnIndex("course_number")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return courseNumbers;
    }
}
