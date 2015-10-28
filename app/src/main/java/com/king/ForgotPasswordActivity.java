package com.king;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.dara.myapplication.R;

import controller.UserController;
import controller.Utils;
import model.UserTable;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameText;
    UserController userController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        usernameText = (EditText)findViewById(R.id.usernameText);
        userController = new UserController(this);
    }

    @Override
    public void onClick(View view) {

    }
    //called when goes back to logout
//    public void gotoLogout() {
//        Intent ForgetIntent = new Intent(this, LoginActivity.class);
//        startActivity(ForgetIntent);
//    }

    public void gotoLogout(View view) {
        Intent ForgetIntent = new Intent(this, LoginActivity.class);
        startActivity(ForgetIntent);
    }

    public void getPassword(View view) {
        UserTable userTable = userController.getDataByUser(usernameText.getText().toString());
        Utils.getInstance().showMessage("",userTable.password,this);
    }
}
