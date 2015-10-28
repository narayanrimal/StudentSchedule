package com.king;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dara.myapplication.R;

import controller.UserController;
import controller.Utils;

public class UserMenuActivity extends AppCompatActivity {

    UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        userController = new UserController(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_menu, menu);
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


    public void gotoEditUser(View view) {
        Intent iIntent = new Intent(this, EditUserActivity.class);
        startActivity(iIntent);
    }

    public void gotoDeleteAccount(View view) {
        boolean result;
        try {
            result = userController.delete(Utils.getInstance().getCurrentUser());
            if (result) {
                Intent iIntent = new Intent(this, LoginActivity.class);
                startActivity(iIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
