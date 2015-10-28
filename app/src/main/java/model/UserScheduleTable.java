package model;

/**
 * Created by Dara on 10/19/2015.
 */
public class UserScheduleTable extends DataTable {
    // Labels table name
    public static final String TABLE = "user_schedule";

    // Labels Table Columns names
    public static final String KEY_user_id = "user_id";
    public static final String KEY_description = "description";
    public static final String KEY_year = "year";
    public static final String KEY_semester = "semester";

    public long user_id;
    public String description;
    public int year;
    public String semester;
}
