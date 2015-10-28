package com.king;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dara.myapplication.R;

import java.util.ArrayList;

import controller.CourseController;
import controller.SelectCourseAdapter;
import controller.UserScheduleController;
import controller.Utils;
import model.CourseTable;
import model.DesiredCourseModel;
import model.UserScheduleDetailsTable;
import model.UserScheduleTable;

public class SelectCourseActivity extends AppCompatActivity {

    SelectCourseActivity selectCourseActivity = this;

    CourseController courseController;
    UserScheduleController userScheduleController;
    EditText description;
    AutoCompleteTextView yearAutoCompleteTextView, semesterAutoCompleteTextView, departmentAutoCompleteTextView, courseNumberAutoCompleteTextView;
    ArrayList<String> departmentArrayList, courseNumberArrayList, courseArrayList;
    ArrayAdapter<String> departmentArrayAdapter, courseNumberArrayAdapter, courseArrayAdapter;
    String[] Years, Semesters, Courses;

    Button addClassButton;

    ListView selectCourseListView;
    DesiredCourseModel list;
    SelectCourseAdapter adapter;
    String[] arrayString = new String[]{"IBM", "TCS", "MCN", "WIPRO", "INFOSYS", "ACCENTURE"};
    ArrayList<DesiredCourseModel> arrayList = new ArrayList<DesiredCourseModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        courseController = new CourseController(this);
        userScheduleController = new UserScheduleController(this);
        departmentArrayList = new ArrayList<>();

//        courseArrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        description = (EditText) findViewById(R.id.description_select_course);

        yearAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.year_select_course);
        Years = getResources().getStringArray(R.array.years);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Years);
        yearAutoCompleteTextView.setThreshold(1);
        yearAutoCompleteTextView.setAdapter(yearAdapter);

        semesterAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.semester_select_course);
        Semesters = getResources().getStringArray(R.array.semesters);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Semesters);
        semesterAutoCompleteTextView.setThreshold(1);
        semesterAutoCompleteTextView.setAdapter(semesterAdapter);

        departmentArrayList = courseController.getDepartment();
        departmentAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.course_department_select_course);
        departmentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, departmentArrayList);
        departmentAutoCompleteTextView.setThreshold(1);
        departmentAutoCompleteTextView.setAdapter(departmentArrayAdapter);

        courseNumberAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.course_number_select_course);
        courseNumberArrayList = courseController.getCouserNumber2();//(departmentAutoCompleteTextView.getText().toString());
        courseNumberArrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, courseNumberArrayList);
        courseNumberAutoCompleteTextView.setThreshold(1);
        courseNumberAutoCompleteTextView.setAdapter(courseNumberArrayAdapter);

        addClassButton = (Button) findViewById(R.id.add_course_select_course);

        selectCourseListView = (ListView) findViewById(R.id.selected_courses_listview);
//        setData();
        Resources resources = getResources();
        adapter = new SelectCourseAdapter(selectCourseActivity, arrayList, resources);
        selectCourseListView.setAdapter(adapter);

        /** Defining a click event listener for the button "Add" */
        View.OnClickListener addClass = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

//        addClassButton.setOnClickListener(addClass);

//        departmentAutoCompleteTextView.setOnItemClickListener(new handleCSE());
//        departmentAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                courseNumberArrayList = courseController.getCouserNumber(departmentAutoCompleteTextView.getText().toString());
//                courseNumberArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,courseNumberArrayList);
//                courseNumberAutoCompleteTextView.setThreshold(1);
//                courseNumberAutoCompleteTextView.setAdapter(courseNumberArrayAdapter);
//            }
//        });

    }

    public void setData() {
        for (int i = 0; i <= arrayString.length - 1; i++) {
            list = new DesiredCourseModel();
            list.setDepartment("companyname :" + arrayString[i]);
//            list.setName("id :" + i);
            arrayList.add(list);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_course, menu);
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

    public void addCourse(View view) {
//        CourseTable courseTable = new CourseTable();
        try {
            list = new DesiredCourseModel();
            String department = departmentAutoCompleteTextView.getText().toString();
            String course_number = courseNumberAutoCompleteTextView.getText().toString();
            CourseTable courseTable = courseController.getCourseByDepartmentAndCourseNumber(department, course_number);

            list.setDepartment(department);
            list.setCourse_number(course_number);
            list.setCourse_title(courseTable.course_description);
            arrayList.add(list);
//                    if (arrayList.size() <= 0) {
//                        v.setVisibility(View.GONE);
//                    } else {
//                        v.setVisibility(View.VISIBLE);
//                    }
            adapter.notifyDataSetChanged();

            departmentAutoCompleteTextView.setText("");
            courseNumberAutoCompleteTextView.setText("");
//                    adapter.getItem()

            // Save Course to User Schedule
            if (Utils.getInstance().getCurrentSchedule() < 1) {
                long user_schedule_id;
                UserScheduleTable userScheduleTable = new UserScheduleTable();
                userScheduleTable.user_id = Utils.getInstance().getCurrentUser();
                userScheduleTable.description = description.getText().toString();
                userScheduleTable.year = Integer.parseInt(yearAutoCompleteTextView.getText().toString());
                userScheduleTable.semester = semesterAutoCompleteTextView.getText().toString();

                user_schedule_id = userScheduleController.insertUserSchedule(userScheduleTable);
                Utils.getInstance().setCurrentSchedule(user_schedule_id);
            }

            UserScheduleDetailsTable userScheduleDetailsTable = new UserScheduleDetailsTable();
            userScheduleDetailsTable.user_schedule_id = Utils.getInstance().getCurrentSchedule();
            userScheduleDetailsTable.user_id = Utils.getInstance().getCurrentUser();
            userScheduleDetailsTable.course_id = courseTable.id; //courseController.getCourseID(departmentAutoCompleteTextView.getText().toString(), courseNumberAutoCompleteTextView.getText().toString());

            userScheduleController.insertUserScheduleDetails(userScheduleDetailsTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    //called when user clicks Need to register
//    class handleCSE implements AdapterView.OnItemClickListener {
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            courseNumberArrayList = courseController.getCouserNumber(departmentAutoCompleteTextView.getText().toString());
//            courseNumberArrayAdapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,courseNumberArrayList);
//            courseNumberAutoCompleteTextView.setThreshold(1);
//            courseNumberAutoCompleteTextView.setAdapter(courseNumberArrayAdapter);
//        }
//    }
}
