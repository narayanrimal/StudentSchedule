package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import model.UserBlockOutTimeTable;
import model.UserScheduleTable;

/**
 * Created by Dara on 10/24/2015.
 */
public class UserBlockOutTimeController {

    DatabaseAdapter databaseAdapter;

    public UserBlockOutTimeController(Context context) {
        databaseAdapter = new DatabaseAdapter(context);
    }

    public long insertUserBlockOutTime(UserBlockOutTimeTable userBlockOutTimeTable) {
        long id = 0;
        try {
            SQLiteDatabase database = databaseAdapter.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("user_id", userBlockOutTimeTable.user_id);
            values.put("day", userBlockOutTimeTable.day);
            values.put("start_time", String.valueOf(userBlockOutTimeTable.start_time));
            values.put("end_time", String.valueOf(userBlockOutTimeTable.end_time));
            values.put("description", userBlockOutTimeTable.description);
            id = database.insertOrThrow("user_schedule", null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
