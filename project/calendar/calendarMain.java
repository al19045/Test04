package com.example.StudySupport;

import android.app.Activity;
import android.content.Context;

import  java.io.*;
import  java.util.*;

public class calendarMain {
    List<String> event = new ArrayList<String>();
    int studyTime = 0;
    List<String> task = new ArrayList<String>();
    boolean check = false;
    Context c;

    calendarMain(Context context){
        c = context;
    }

    void get(int year,int month, int day){
        manageCalendar cal = new manageCalendar(c);
        event = cal.get(year,month,day);
        Calendar date;
        date.YEAR = year;
        date.MONTH = month;
        date.DATE = day;
        //DailyStudyTime s = new DailyStudyTime();
        //studyTime = s.read(date);
        //Manage_ToDo t = new Manage_ToDo();
        //task = Manage_ToDo.getTask(date);

    }
    boolean add(int year, int month, int day, String event_name){
        boolean check = false;
        manageCalendar cal = new manageCalendar(c);
        check = cal.add(year,month,day,event_name);
        return check;
    }
    boolean delete(int year,int month,int day,String event_name){
        manageCalendar cal = new manageCalendar(c);
        return cal.delete(year,month,day,event_name);
    }
}
