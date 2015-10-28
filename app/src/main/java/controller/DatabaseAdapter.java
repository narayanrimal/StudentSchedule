package controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Dara on 10/12/2015.
 */
public class DatabaseAdapter extends SQLiteOpenHelper  {


        private static final String DATABASE_NANME = "student.db";
//        private static final  String USER_TABLE = "users";
//        private static final String UID = "_id";
//        private static final String NAME = "Name";
        private static final int DATABASE_VERSION = 1;

        private static final String LOGCAT = null;

        public DatabaseAdapter(Context context){
            super(context, Environment.getExternalStorageDirectory()
                    + File.separator + DATABASE_NANME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                String query;
                query = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR(255), password VARCHAR(255), email VARCHAR(255))";
                db.execSQL(query);
                query = "CREATE TABLE course(id INTEGER PRIMARY KEY AUTOINCREMENT, course_department VARCHAR(255), course_number INTEGER, course_description VARCHAR(255))";
                db.execSQL(query);
                query = "CREATE TABLE user_course(user_id INTEGER, course_id INTEGER)";
                db.execSQL(query);

                // reguiired data to start up application
                db.execSQL("INSERT INTO users VALUES(null,'dara','123','vorn.dara@gmail.com');");

                db.execSQL("INSERT INTO course VALUES(null,'CSE',1310,'Intro to Programming');");

                Log.d(LOGCAT, "users table Created");
            } catch (SQLException e){

                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int version_old, int current_version) {
            try{
                String query;
                query = "DROP TABLE IF EXISTS users";
                db.execSQL(query);
                query = "DROP TABLE IF EXISTS course";
                db.execSQL(query);
                query = "DROP TABLE IF EXISTS user_course";
                db.execSQL(query);
                Log.d(LOGCAT, "delete tables");
                onCreate(db);
            }catch (SQLException e){

                e.printStackTrace();
            }

        }
//    }

}
