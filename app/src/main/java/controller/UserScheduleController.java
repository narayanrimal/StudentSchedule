package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import model.UserScheduleDetailsTable;
import model.UserScheduleTable;

/**
 * Created by Dara on 10/19/2015.
 */
public class UserScheduleController {

    DatabaseAdapter databaseAdapter;

    public UserScheduleController(Context context) {
        databaseAdapter = new DatabaseAdapter(context);
    }

    public long insertUserSchedule(UserScheduleTable userScheduleTable) {
        long id = 0;
        try {
            SQLiteDatabase database = databaseAdapter.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("user_id", userScheduleTable.user_id);
            values.put("description", userScheduleTable.description);
            values.put("year", userScheduleTable.year);
            values.put("semester", userScheduleTable.semester);
            id = database.insertOrThrow("user_schedule", null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public long insertUserScheduleDetails(UserScheduleDetailsTable userScheduleDetailsTable) {
        long id = 0;
        try {
            SQLiteDatabase database = databaseAdapter.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("user_schedule_id", userScheduleDetailsTable.user_schedule_id);
            values.put("user_id", userScheduleDetailsTable.user_id);
            values.put("course_id", userScheduleDetailsTable.course_id);
            id = database.insertOrThrow("user_schedule_details", null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
