package com.king;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dara.myapplication.R;

import controller.UserController;
import controller.Utils;
import model.UserTable;

public class EditUserActivity extends AppCompatActivity {
    EditText usernameText, passwordText, confirmPasswordText, emailText;
    UserController userController;
    String tempUserName;
    UserTable tempUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        usernameText = (EditText) findViewById(R.id.usernameEditUserText);
        passwordText = (EditText) findViewById(R.id.passwordEditUserText);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordEditUserText);
        emailText = (EditText) findViewById(R.id.emailEditUserText);
        userController = new UserController(this);
        try {
            UserTable userTable = userController.getDataById(Utils.getInstance().getCurrentUser());
            usernameText.setText(userTable.userName);
            passwordText.setText(userTable.password);
            confirmPasswordText.setText(userTable.password);
            emailText.setText(userTable.email);
            tempUserName = userTable.userName;
            tempUser = userTable;
//            email.setText(userTable.email, TextView.BufferType.EDITABLE);
//        Utils.getInstance().showMessage("",userTable.password,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user, menu);
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

    public void UpdateUser(View view) {
        boolean result;

        UserTable userTable = new UserTable();
        userTable.id = Utils.getInstance().getCurrentUser();
        userTable.userName = usernameText.getText().toString();
        userTable.password = passwordText.getText().toString();
        userTable.email = emailText.getText().toString();

        UserTable tempSearchUser = userController.getDataByUser(userTable.userName);

        if(tempUser.userName.equals(usernameText.getText().toString())
                && tempUser.password.equals(passwordText.getText().toString())
                && tempUser.password.equals(confirmPasswordText.getText().toString())
                && tempUser.email.equals(emailText.getText().toString())                )
        {
            Intent iIntent = new Intent(this, UserMenuActivity.class);
            startActivity(iIntent);
        }

        if ( tempSearchUser.userName != null && !tempUser.userName.equals(usernameText.getText().toString())) {
            Utils.getInstance().showMessage("Error", "User Name is already used!", this);
            return;
        }

        if (!passwordText.getText().toString().equals(confirmPasswordText.getText().toString())) {
            Utils.getInstance().showMessage("Error", "Password does not match!", this);
            return;
        }
        try {
            result = userController.update(userTable);
            if (result) {
//                Utils.getInstance().showMessage("", "succ", this);
                Intent iIntent = new Intent(this, UserMenuActivity.class);
                startActivity(iIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
