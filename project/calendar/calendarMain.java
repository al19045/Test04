//イベント情報管理部(山中)
package com.example.calendar;

import  java.io.*;
import  java.util.*;

public class calendarMain {
    List<String> event = new ArrayList<String>();
    int studyTime = 0;
    List<String> task = new ArrayList<String>();
    boolean check = false;
    void get(int year,int month, int day){
        manageCalendar cal = new manageCalendar();
        //DailyStudyTime time = new DailyStudyTime();
        //saveTask todo = new saveTask();
        event = cal.get(year,month,day);
        //study_time = time.Read(year,month,day);
        //task = todo.getTask(year,month,day);
    }
    boolean add(int year,int month,int day,String event_name){
        manageCalendar cal = new manageCalendar();
        return cal.add(year,month,day,event_name);
    }
    boolean delete(int year,int month,int day,String event_name){
        manageCalendar cal = new manageCalendar();
        return cal.delete(year,month,day,event_name);
    }
}
