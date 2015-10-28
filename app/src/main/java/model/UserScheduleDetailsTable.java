package model;

/**
 * Created by Dara on 10/19/2015.
 */
public class UserScheduleDetailsTable {
    public static final String TABLE = "user_schedule_details";

    // Labels Table Columns names
    public static final String KEY_schedule_id = "user_schedule_id";
    public static final String KEY_user_id = "user_id";
    public static final String KEY_course_id = "course_id";

    public long user_schedule_id;
    public long user_id;
    public long course_id;
}
