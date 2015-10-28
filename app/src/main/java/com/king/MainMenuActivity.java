package com.king;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dara.myapplication.R;


/**
 * Created by Dara on 10/13/2015.
 */
public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if(id == R.id.action_logout){
            this.gotoLogout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }

    //called when user clicks Need to register
    public void logout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    //called when goes back to logout
    public void gotoLogout() {
        Intent iIntent = new Intent(this, LoginActivity.class);
        startActivity(iIntent);
    }

    public void gotoUserMenu(View view) {
        Intent iIntent = new Intent(this, UserMenuActivity.class);
        startActivity(iIntent);
    }

    public void gotoCourseMenu(View view) {
        Intent iIntent = new Intent(this, CourseMenuActivity.class);
        startActivity(iIntent);
    }

    public void gotBlockOut(View view) {

        Intent iIntent = new Intent(this, BlockOutTimeActivity.class);
        startActivity(iIntent);
    }
}
