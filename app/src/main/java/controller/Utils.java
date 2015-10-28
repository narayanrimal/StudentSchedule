package controller;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Dara on 10/10/2015.
 */
public class Utils {
    private static Utils instance;
    // Global variable
    private long userID;
    private long scheduleID;

    // Restrict the constructor from being instantiated
    private Utils(){}


    public static synchronized Utils getInstance(){
        if(instance==null){
            instance=new Utils();
        }
        return instance;
    }

    public void showMessage(String title,String message, Context object)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(object);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void setCurrentUser(long user){
        this.userID=user;
    }
    public long getCurrentUser(){
        return this.userID;
    }

    public void setCurrentSchedule(long schedule){
        this.scheduleID=schedule;
    }
    public long getCurrentSchedule(){
        return this.scheduleID;
    }
}
