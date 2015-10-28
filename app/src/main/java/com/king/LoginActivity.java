package com.king;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dara.myapplication.R;

import java.io.File;

import controller.UserController;
import controller.Utils;
import model.UserTable;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    EditText usernameText, passwordText;
    TextView register;
    Button loginButton;
    SQLiteDatabase db;
    UserController userController;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        register = (TextView) findViewById(R.id.textView);
        loginButton = (Button) findViewById(R.id.loginButton);

        userController = new UserController(this);

        loginButton.setOnClickListener(this);
        register.setOnClickListener(new handleRegister());

        try {
            db = openOrCreateDatabase(Environment.getExternalStorageDirectory()
                    + File.separator + "student.db", Context.MODE_PRIVATE, null);
//            start_up();
//            db.execSQL("DROP TABLE IF EXISTS users");
//            db.execSQL("CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR(255), password VARCHAR(255), email VARCHAR(255))");
//            db.execSQL("DELETE FROM users");
//            db.execSQL("INSERT INTO users VALUES(null,'dara','123','');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            if (usernameText.getText().toString().trim().length() == 0 ||
                    passwordText.getText().toString().trim().length() == 0) {
                Utils.getInstance().showMessage("Error", "Please enter all values", this);
                return;
            }
            Cursor c = userController.login(usernameText.getText().toString(), passwordText.getText().toString());
            if (c.getCount() == 0) {
                Utils.getInstance().showMessage("Error", "Invalid User Name or Password!", this);
                return;
            } else {
                //set current user
                UserTable userTable = userController.getDataByUser(usernameText.getText().toString());
                Utils.getInstance().setCurrentUser(userTable.id);

                Intent ForgetIntent = new Intent(this, MainMenuActivity.class);
                startActivity(ForgetIntent);
//                Utils.getInstance().showMessage("Success", "login success", this);
            }
        }
    }

    public void onCreateTable() {
        try {
            String query;
            query = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name VARCHAR(255), last_name VARCHAR(255), user VARCHAR(255), password VARCHAR(255), email VARCHAR(255))";
            db.execSQL(query);
            query = "CREATE TABLE course(id INTEGER PRIMARY KEY AUTOINCREMENT, course_department VARCHAR(255), course_number INTEGER, course_description VARCHAR(255), credit INTEGER, study_hour INTEGER)";
            db.execSQL(query);
//            query = "CREATE TABLE user_course(user_id INTEGER, course_id INTEGER)";
//            db.execSQL(query);
            query = "CREATE TABLE user_schedule(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, description VARCHAR(255), year INTEGER, semester VARCHAR(255))";
            db.execSQL(query);
            query = "CREATE TABLE user_schedule_details(user_schedule_id INTEGER, user_id INTEGER, course_id INTEGER)";
            db.execSQL(query);

            query = "CREATE TABLE user_block_out_time(user_id INTEGER, day VARCHAR(255), start_time DATETIME, end_time DATETIME, description VARCHAR(255))";
            db.execSQL(query);

            // reguiired data to start up application
            db.execSQL("INSERT INTO users VALUES(null,'Dara', 'Krang', 'dara','123','vorn.dara@gmail.com');");

            db.execSQL("INSERT INTO course VALUES(null,'CSE',1310,'Intro to Programming',3,9);");
            db.execSQL("INSERT INTO course VALUES(null,'CSE',1105,'Intro to CSE',1,3);");
            db.execSQL("INSERT INTO course VALUES(null,'CSE',1320,'Intermediate Programming',3,9);");
            db.execSQL("INSERT INTO course VALUES(null,'CSE',1325,'Object-Oriented Programming',3,9);");
            db.execSQL("INSERT INTO course VALUES(null,'CSE',2312,'Computer Organization',3,9);");
            db.execSQL("INSERT INTO course VALUES(null,'CSE',2100,'Practical Systems',3,9);");
            db.execSQL("INSERT INTO course VALUES(null,'MATH',1426,'Calculus I',4,12);");
            db.execSQL("INSERT INTO course VALUES(null,'MATH',2425,'Calculus II',4,12);");
            db.execSQL("INSERT INTO course VALUES(null,'PHYS',1443,'Technical Physics I',4,12);");
            db.execSQL("INSERT INTO course VALUES(null,'PHYS',1444,'Technical Physics II',4,12);");


        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void start_up() {
        try {
            String query;
            query = "DROP TABLE IF EXISTS users";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS course";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS user_course";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS user_schedule";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS user_schedule_details";
            db.execSQL(query);
            query = "DROP TABLE IF EXISTS user_block_out_time";
            db.execSQL(query);
            onCreateTable();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    //called when user clicks Need to register
    class handleRegister implements OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
            startActivity(intent);
        }
    }

    //    //called when user clicks Need to register
//    class handleForgotPassword implements OnClickListener {
//        public void onClick(View v) {
//            Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
//            startActivity(intent);
//        }
//    }
    //called when user clicks Need to register
    public void gotoForget(View view) {
        Intent ForgetIntent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(ForgetIntent);
    }

    //called when user clicks Need to register
//    public void gotoLogout(View view) {
//        Intent ForgetIntent = new Intent(this, LoginActivity.class);
//        startActivity(ForgetIntent);
//    }
}
