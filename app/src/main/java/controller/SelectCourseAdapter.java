package controller;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.dara.myapplication.R;

import java.util.ArrayList;

import model.DesiredCourseModel;

public class SelectCourseAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    ArrayList arrayList;
    Resources resource;
    DesiredCourseModel tempValues;

    public SelectCourseAdapter(Activity activty, ArrayList arrayList, Resources resource) {
        this.activity = activty;
        this.arrayList = arrayList;
        this.resource = resource;

        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        if (arrayList.size() <= 0)
            return 1;
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder {
        public TextView text;
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public Button button1;
    }

    /**
     * *** Depends upon data size called for each row , Create each ListView row ****
     */
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.desired_courses_listview, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new ViewHolder();
            holder.text1 = (TextView) vi.findViewById(R.id.desiredCourseDepartment);
            holder.text2 = (TextView) vi.findViewById(R.id.desiredCourseNumber);
            holder.text3 = (TextView) vi.findViewById(R.id.desiredCourseTitle);
            holder.button1 = (Button) vi.findViewById(R.id.desiredCourseButton);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (arrayList.size() <= 0) {
//            holder.text.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (DesiredCourseModel) arrayList.get(position);

            /************  Set Model values in Holder elements ***********/

            holder.text1.setText(tempValues.getDepartment());
            holder.text2.setText(tempValues.getCourse_number());
            holder.text3.setText(tempValues.getCourse_title());

            /******** Set Item Click Listner for LayoutInflater for each row *******/
            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        arrayList.remove(getItem(position));
                        notifyDataSetChanged();
//                    notifyDataSetInvalidated();
                        if (arrayList.size() <= 0) {
                            convertView.setVisibility(View.GONE);
                        } else {
                            convertView.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        return vi;
    }


}