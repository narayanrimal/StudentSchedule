package com.king;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.dara.myapplication.R;

import java.util.ArrayList;

import controller.BlockOutTimeAdapter;
import controller.SelectCourseAdapter;
import model.BlockOutTimeModel;
import model.CourseTable;
import model.DesiredCourseModel;

/**
 * Created by Dara on 10/23/2015.
 */
public class BlockOutTimeActivity extends AppCompatActivity {
    BlockOutTimeActivity blockOutTimeActivity = this;

    ToggleButton mondayToggleButton, tuesdayToggleButton, wednesdayToggleButton, thursdayToggleButton, fridayToggleButton, saturdayToggleButton;
    TimePicker startTimePicker, endTimePicker;

    ListView blockOutTimeListView;
    BlockOutTimeAdapter blockOutTimeAdapter;
    ArrayList<BlockOutTimeModel> blockOutTimeModelArrayList = new ArrayList<BlockOutTimeModel>();
    BlockOutTimeModel blockOutTimeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blocked_time);

        startTimePicker = (TimePicker) findViewById(R.id.start_timePicker);
        endTimePicker = (TimePicker)findViewById(R.id.end_timePicker);

        mondayToggleButton = ((ToggleButton) findViewById(R.id.monday_toggleButton));
        tuesdayToggleButton = ((ToggleButton) findViewById(R.id.tuesday_toggleButton));
        wednesdayToggleButton = ((ToggleButton) findViewById(R.id.wednesday_toggleButton));
        thursdayToggleButton = ((ToggleButton) findViewById(R.id.thursday_toggleButton));
        fridayToggleButton = ((ToggleButton) findViewById(R.id.friday_toggleButton));
        saturdayToggleButton = ((ToggleButton) findViewById(R.id.saturday_toggleButton));

        blockOutTimeListView = (ListView) findViewById(R.id.block_out_times_listView);
//        setData();
        Resources resources = getResources();
        blockOutTimeAdapter = new BlockOutTimeAdapter(blockOutTimeActivity, blockOutTimeModelArrayList, resources);
        blockOutTimeListView.setAdapter(blockOutTimeAdapter);

        mondayToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setBackgroundColor(getResources().getColor(R.color.utaOrange));
                } else
                    buttonView.setBackgroundColor(getResources().getColor(R.color.button_material_light));
            }
        });

        tuesdayToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setBackgroundColor(getResources().getColor(R.color.utaOrange));
                }
                else
                    buttonView.setBackgroundColor(getResources().getColor(R.color.button_material_light));
            }
        });

        wednesdayToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setBackgroundColor(getResources().getColor(R.color.utaOrange));
                }
                else
                    buttonView.setBackgroundColor(getResources().getColor(R.color.button_material_light));
            }
        });

        thursdayToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setBackgroundColor(getResources().getColor(R.color.utaOrange));
                }
                else
                    buttonView.setBackgroundColor(getResources().getColor(R.color.button_material_light));
            }
        });

        fridayToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setBackgroundColor(getResources().getColor(R.color.utaOrange));
                }
                else
                    buttonView.setBackgroundColor(getResources().getColor(R.color.button_material_light));
            }
        });

        saturdayToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setBackgroundColor(getResources().getColor(R.color.utaOrange));
                }
                else
                    buttonView.setBackgroundColor(getResources().getColor(R.color.button_material_light));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_menu, menu);
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

    public void saveBlockoutTimes(View view) {
        String day;
        if(mondayToggleButton.isChecked()){
            day = "Monday";
        }



    }

    public void addBlockoutTime(View view) {

        String start_time_am_pm, end_time_am_pm ;

        StringBuilder start_time = new StringBuilder().append(pad(startTimePicker.getCurrentHour()))
                .append(":").append(pad(startTimePicker.getCurrentMinute()));
        StringBuilder end_time = new StringBuilder().append(pad(endTimePicker.getCurrentHour()))
                .append(":").append(pad(endTimePicker.getCurrentMinute()));
//        String department = departmentAutoCompleteTextView.getText().toString();
//        String course_number = courseNumberAutoCompleteTextView.getText().toString();
//        CourseTable courseTable = courseController.getCourseByDepartmentAndCourseNumber(department, course_number);

//        list.setDepartment(department);
//        list.setCourse_number(course_number);
//        list.setCourse_title(courseTable.course_description);
        if(mondayToggleButton.isChecked()) {
            blockOutTimeModel  = new BlockOutTimeModel();
            blockOutTimeModel.setDay("Monday");
            blockOutTimeModel.setStart_time(start_time.toString());
            blockOutTimeModel.setEnd_time(end_time.toString());
            blockOutTimeModelArrayList.add(blockOutTimeModel);
        }

        if(tuesdayToggleButton.isChecked()) {
            blockOutTimeModel  = new BlockOutTimeModel();
            blockOutTimeModel.setDay("Tuesday");
            blockOutTimeModel.setStart_time(start_time.toString());
            blockOutTimeModel.setEnd_time(end_time.toString());
            blockOutTimeModelArrayList.add(blockOutTimeModel);
        }

        if(wednesdayToggleButton.isChecked()) {
            blockOutTimeModel  = new BlockOutTimeModel();
            blockOutTimeModel.setDay("Wednesday");
            blockOutTimeModel.setStart_time(start_time.toString());
            blockOutTimeModel.setEnd_time(end_time.toString());
            blockOutTimeModelArrayList.add(blockOutTimeModel);
        }

        if(thursdayToggleButton.isChecked()) {
            blockOutTimeModel  = new BlockOutTimeModel();
            blockOutTimeModel.setDay("Thursday");
            blockOutTimeModel.setStart_time(start_time.toString());
            blockOutTimeModel.setEnd_time(end_time.toString());
            blockOutTimeModelArrayList.add(blockOutTimeModel);
        }

        if(fridayToggleButton.isChecked()) {
            blockOutTimeModel  = new BlockOutTimeModel();
            blockOutTimeModel.setDay("Friday");
            blockOutTimeModel.setStart_time(start_time.toString());
            blockOutTimeModel.setEnd_time(end_time.toString());
            blockOutTimeModelArrayList.add(blockOutTimeModel);
        }

        if(saturdayToggleButton.isChecked()) {
            blockOutTimeModel  = new BlockOutTimeModel();
            blockOutTimeModel.setDay("Saturday");
            blockOutTimeModel.setStart_time(start_time.toString());
            blockOutTimeModel.setEnd_time(end_time.toString());
            blockOutTimeModelArrayList.add(blockOutTimeModel);
        }

        blockOutTimeAdapter.notifyDataSetChanged();
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}