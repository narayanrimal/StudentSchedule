package controller;

/**
 * Created by Dara on 10/11/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.database.SQLException;

import java.util.HashMap;

import model.UserTable;

//import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class UserController {
    DatabaseAdapter databaseAdapter;

    public UserController(Context context) {
        databaseAdapter = new DatabaseAdapter(context);
    }

    public Cursor login(String userName, String password) {
        SQLiteDatabase database = databaseAdapter.getReadableDatabase();
        String query = "SELECT * FROM users WHERE user='" + userName + "' AND password='" + password + "'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public Cursor getPassword(String userName) {
        SQLiteDatabase database = databaseAdapter.getReadableDatabase();
        String query = "SELECT password FROM users WHERE user='" + userName + "'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    //Retrieving data
    public UserTable getDataById(long id) {
        UserTable data = new UserTable();
        SQLiteDatabase db = databaseAdapter.getReadableDatabase();
        try {
            Cursor cursor = db.query("users", new String[]{"id", "user", "password", "email"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();
            data.id = cursor.getLong(0);
            data.userName = cursor.getString(1);
            data.password = cursor.getString(2);
            data.email = cursor.getString(3);
            db.close();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public UserTable getDataByUser(String userName) {
        UserTable data = new UserTable();
        SQLiteDatabase db = databaseAdapter.getReadableDatabase();
        try {
            Cursor cursor = db.query("users", new String[]{"id", "user", "password", "email"}, "user=?", new String[]{String.valueOf(userName)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();
            data.id = cursor.getLong(0);
            data.userName = cursor.getString(1);
            data.password = cursor.getString(2);
            data.email = cursor.getString(3);
            db.close();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public long insertData(UserTable userTable) {
        long id = 0;
        try {
            SQLiteDatabase database = databaseAdapter.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("user", userTable.userName);
            values.put("password", userTable.password);
            values.put("email", userTable.email);
            id = database.insertOrThrow("users", null, values);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }

//    public int updateStudent(HashMap<String, String> queryValues) {
//        SQLiteDatabase database = databaseAdapter.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("StudentName", queryValues.get("StudentName"));
//        return database.update("Students", values, "StudentId" + " = ?", new String[]{queryValues.get("StudentId")}); //String updateQuery = "Update words set txtWord='"+word+"' where txtWord='"+ oldWord +"'"; //Log.d(LOGCAT,updateQuery); //database.rawQuery(updateQuery, null); //return database.update("words", values, "txtWord = ?", new String[] { word }); } public void deleteStudent(String id) { Log.d(LOGCAT,"delete"); SQLiteDatabase database = this.getWritableDatabase(); String deleteQuery = "DELETE FROM Students where StudentId='"+ id +"'"; Log.d("query",deleteQuery);	database.execSQL(deleteQuery); } public ArrayList<HashMap<String, String>> getAllStudents() { ArrayList<HashMap<String, String>> wordList; wordList = new ArrayList<HashMap<String, String>>(); String selectQuery = "SELECT * FROM Students"; SQLiteDatabase database = this.getWritableDatabase(); Cursor cursor = database.rawQuery(selectQuery, null); if (cursor.moveToFirst()) { do { HashMap<String, String> map = new HashMap<String, String>(); map.put("StudentId", cursor.getString(0)); map.put("StudentName", cursor.getString(1)); wordList.add(map); } while (cursor.moveToNext()); } // return contact list return wordList; } public HashMap<String, String> getStudentInfo(String id) { HashMap<String, String> wordList = new HashMap<String, String>(); SQLiteDatabase database = this.getReadableDatabase(); String selectQuery = "SELECT * FROM Students where StudentId='"+id+"'"; Cursor cursor = database.rawQuery(selectQuery, null); if (cursor.moveToFirst()) { do { //HashMap<String, String> map = new HashMap<String, String>(); wordList.put("StudentName", cursor.getString(1)); //wordList.add(map); } while (cursor.moveToNext()); }	return wordList; }	}
//    }

    public boolean update(UserTable userTable) {

        SQLiteDatabase db = databaseAdapter.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;
        try {
            values.put(userTable.KEY_user, userTable.userName);
            values.put(userTable.KEY_password, userTable.password);
            values.put(userTable.KEY_email, userTable.email);

            result = db.update(userTable.TABLE, values, userTable.KEY_ID + "=" + userTable.id, null) > 0;
            db.close(); // Closing database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(long id) {

        SQLiteDatabase db = databaseAdapter.getWritableDatabase();
        boolean result = false;
        try {
            result = db.delete("users", "id=" + String.valueOf(id), null) >0; //update(userTable.TABLE, values, userTable.KEY_ID + "=" + userTable.id, null) > 0 ;
            db.close(); // Closing database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}