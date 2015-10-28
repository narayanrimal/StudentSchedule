package model;

import java.util.Date;

/**
 * Created by Dara on 10/24/2015.
 */
public class UserBlockOutTimeTable {

    // Labels table name
    public static final String TABLE = "user_block_out_time";

    // Labels Table Columns names
    public static final String KEY_user_id = "user_id";
    public static final String KEY_description = "description";
    public static final String KEY_start_time = "start_time";
    public static final String KEY_end_time = "end_time";

    public long user_id;
    public String day;
    public Date start_time;
    public Date end_time;
    public String description;
}
