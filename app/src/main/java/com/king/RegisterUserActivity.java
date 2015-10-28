package com.king;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dara.myapplication.R;

import controller.DatabaseAdapter;
import controller.UserController;
import controller.Utils;
import model.UserTable;

/**
 * Created by Dara on 10/10/2015.
 */
public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usernameText,passwordText,confirmPasswordText,emailText;
    Button registerButton;
//    SQLiteDatabase db;
    UserTable userTable;
    UserController userController;
//    DatabaseAdapter dbHelper;
    long userID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        usernameText = (EditText)findViewById(R.id.usernameRegisterEditText);
        passwordText = (EditText)findViewById(R.id.passwordRegisterEditText);
        confirmPasswordText = (EditText)findViewById(R.id.confirmPasswordRegisterEditText);
        emailText = (EditText)findViewById(R.id.emailRegisterEditText);
        registerButton = (Button)findViewById(R.id.registerButton);
        userTable = new UserTable();

//        dbHelper = new DatabaseAdapter(this);
        userController = new UserController(this);
        registerButton.setOnClickListener(this);

//        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            if(usernameText.getText().toString().trim().length() ==0 ||
                    passwordText.getText().toString().trim().length() ==0 ||
                    confirmPasswordText.getText().toString().trim().length() ==0 ||
                    emailText.getText().toString().trim().length() ==0
                    ){
                Utils.getInstance().showMessage("Error", "Please enter all values",this);
                return;
            }
            if (!passwordText.getText().toString().equals(confirmPasswordText.getText().toString()))
            {
                Utils.getInstance().showMessage("Error", "Password does not match!",this);
            }
            userTable.userName = usernameText.getText().toString();
            userTable.password = passwordText.getText().toString();
            userTable.email = emailText.getText().toString();
            userID = userController.insertData(userTable);
//            db.execSQL("INSERT INTO users VALUES(null,'" + usernameText.getText() + "','" + passwordText.getText() +
//                    "','" + emailText.getText() + "');");
            Utils.getInstance().showMessage("Success", "Record added " + userID,this);
//            clearText();
        }
    }
}
