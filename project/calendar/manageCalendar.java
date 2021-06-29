package com.example.StudySupport;


import android.content.Context;
import  java.io.*;
import java.nio.charset.StandardCharsets;
import  java.util.*;

public class manageCalendar {
  Context c;
  static String filename = "eventfile.csv";

  manageCalendar(Context context){
    c = context;
  }

  List<String> get(int year, int month, int day) {
    List<String> event = new ArrayList<String>();
    String line;
    new AppContext();
    Context context = AppContext.getAppContext();
    File file = new File(context.getFilesDir(), filename);
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        if (Integer.parseInt(data[0]) == year && Integer.parseInt(data[1]) == month && Integer.parseInt(data[2]) == day) {
          event.add(data[3]);
        }
      }
      br.close();
    } catch (IOException e) {
      event.add("ファイル読み込み失敗");
    }finally {
      return event;
    }
  }

  boolean add(int year,int month,int day, String event_name){
    String txt = year + "," + month + "," + day + "," + event_name;
    new AppContext();
    Context context = AppContext.getAppContext();
    File file = new File(context.getFilesDir(), filename);
    try{
      OutputStream os = AppContext.getAppContext().openFileOutput(filename, Context.MODE_PRIVATE|Context.MODE_APPEND);
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
      pw.println(txt);
      return true;
    }catch(IOException e){
        return false;
    }
  }

  boolean delete(int year,int month,int day,String event_name){
    boolean check = false;
    List<String> save = new ArrayList<>();
    String line;
    new AppContext();
    Context context = AppContext.getAppContext();

    File file = new File(context.getFilesDir(), filename);
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) {
        String[] data = line.split(",");
        if (Integer.parseInt(data[0]) != year || Integer.parseInt(data[1]) != month || Integer.parseInt(data[2]) != day || !(event_name.matches(data[3]))){
          save.add(line);
        }
      }
      OutputStream os = AppContext.getAppContext().openFileOutput(filename, Context.MODE_PRIVATE);
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
      for(int i = 0; i < save.size(); i++){
        pw.println(save.get(i));
      }
      br.close();
      check = true;
    } catch (IOException e) {
      check = false;
    }finally{
      return check;
    }
  }
}
