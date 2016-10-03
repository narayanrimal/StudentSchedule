package com.king;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dara.myapplication.R;

import controller.IncorporateTimeController;
import controller.Utils;
import model.IncorporateTimeTable;

public class IncorporateTimeActivity extends AppCompatActivity {

    IncorporateTimeController incorporateTimeController;
    EditText sleepEditText, studyEditText, workEditText, commuteEditText;
    Button button;
    long incorporateTimeID;
    IncorporateTimeTable incorporateTimeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorporate_time);

        incorporateTimeController = new IncorporateTimeController(this);
        sleepEditText = (EditText) findViewById(R.id.sleepHoursEditText);
        studyEditText = (EditText) findViewById(R.id.studyHoursEditText);
        workEditText = (EditText) findViewById(R.id.workHoursEditText);
        commuteEditText = (EditText) findViewById(R.id.commuteHoursEditText);
        button = (Button) findViewById(R.id.addIncorporateTimeButton);
        incorporateTimeTable = incorporateTimeController.getIncorporateTimeByUserID(Utils.getInstance().getCurrentUser());

        if (incorporateTimeTable.id > 0) {
            sleepEditText.setText(String.valueOf(incorporateTimeTable.sleep_per_day));
            studyEditText.setText(String.valueOf(incorporateTimeTable.study_per_week));
            workEditText.setText(String.valueOf(incorporateTimeTable.work_per_week));
            commuteEditText.setText(String.valueOf(incorporateTimeTable.commute_per_day));
            button.setText("UPDATE");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        setTitle("Incorporate Time");
        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0); //fix the color to white
            item.setTitle(spanString);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_logout){
            this.gotoLogout();
        }else if(id == R.id.action_main){
            this.gotoMainMenu();
        }

        return super.onOptionsItemSelected(item);
    }
    public void gotoLogout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void gotoMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void onClickButton(View view) {
        int sleepHours;
        if(sleepEditText.getText().equals("")
                || studyEditText.getText().equals("")
                || workEditText.getText().equals("")){
            Utils.getInstance().showMessage("Error","Please input all data!",this);
            return;
        }
        sleepHours = Integer.parseInt(sleepEditText.getText().toString());
        if(sleepHours < 6){
            Utils.getInstance().showMessage("Error","Sleep hours have to be at least 6.",this);
            return;
        }
        incorporateTimeTable.user_id = Utils.getInstance().getCurrentUser();
        incorporateTimeTable.sleep_per_day = sleepHours;
        incorporateTimeTable.study_per_week = Integer.parseInt(studyEditText.getText().toString());
        incorporateTimeTable.work_per_week = Integer.parseInt(workEditText.getText().toString());
        incorporateTimeTable.commute_per_day = Integer.parseInt(commuteEditText.getText().toString());
        if(button.getText().toString().equals("ADD")){
            incorporateTimeController.insertData(incorporateTimeTable);
        }else{
            incorporateTimeController.update(incorporateTimeTable);
        }
        gotoMainMenu();
    }
}
