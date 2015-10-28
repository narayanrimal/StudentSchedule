package model;

/**
 * Created by Dara on 10/11/2015.
 */
public class UserTable extends DataTable {
    // Labels table name
    public static final String TABLE = "users";

    // Labels Table Columns names
    public static final String KEY_user = "user";
    public static final String KEY_email = "email";
    public static final String KEY_password = "password";

    public String userName;
    public String password;
    public String email;
}
