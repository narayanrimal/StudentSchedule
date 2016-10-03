package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import model.IncorporateTimeTable;


public class IncorporateTimeController {
    DatabaseAdapter databaseAdapter;

    public IncorporateTimeController(Context context) {
        databaseAdapter = new DatabaseAdapter(context);
    }
    public long insertData(IncorporateTimeTable incorporateTimeTable) {
        long id = 0;
        try {
            SQLiteDatabase database = databaseAdapter.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("user_id", incorporateTimeTable.user_id);
            values.put("sleep_per_day", incorporateTimeTable.sleep_per_day);
            values.put("study_per_week", incorporateTimeTable.study_per_week);
            values.put("work_per_week", incorporateTimeTable.work_per_week);
            values.put("commute_per_day", incorporateTimeTable.commute_per_day);
            id = database.insertOrThrow("incorporate_time", null, values);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return id;
    }
    public boolean update(IncorporateTimeTable incorporateTimeTable) {

        SQLiteDatabase db = databaseAdapter.getWritableDatabase();
        ContentValues values = new ContentValues();
            boolean result = false;
            try {
            values.put("sleep_per_day", incorporateTimeTable.sleep_per_day);
            values.put("study_per_week", incorporateTimeTable.study_per_week);
            values.put("work_per_week", incorporateTimeTable.work_per_week);
                values.put("commute_per_day", incorporateTimeTable.commute_per_day);
            result = db.update("incorporate_time", values, "user_id=" + incorporateTimeTable.user_id, null) > 0;
            db.close(); // Closing database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public long getIncorporateTimeIDByUserID(long userID){
        long id =0;
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT _id FROM incorporate_time WHERE user_id=" + userID ;
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                id =Long.parseLong(cursor.getString(cursor.getColumnIndex("_id")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public IncorporateTimeTable getIncorporateTimeByUserID(long userID){
        IncorporateTimeTable incorporateTimeTable = new IncorporateTimeTable();
        try {
            SQLiteDatabase database = databaseAdapter.getReadableDatabase();
            String query = "SELECT * FROM incorporate_time WHERE user_id=" + userID ;
            Cursor cursor = database.rawQuery(query, null);
            while(cursor.moveToNext()){
                incorporateTimeTable.id =cursor.getLong(cursor.getColumnIndex("_id"));
                incorporateTimeTable.user_id = cursor.getLong(cursor.getColumnIndex("user_id"));
                incorporateTimeTable.sleep_per_day = cursor.getInt(cursor.getColumnIndex("sleep_per_day"));
                incorporateTimeTable.work_per_week = cursor.getInt(cursor.getColumnIndex("work_per_week"));
                incorporateTimeTable.study_per_week = cursor.getInt(cursor.getColumnIndex("study_per_week"));
                incorporateTimeTable.commute_per_day = cursor.getInt(cursor.getColumnIndex("commute_per_day"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return incorporateTimeTable;
    }
}
