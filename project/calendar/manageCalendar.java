//イベント情報管理部(山中)
package com.example.calendar;

import  java.io.*;
import  java.util.*;

public class manageCalendar {
  List<String> event = new ArrayList<String>();
  boolean check = false;
  List<String> get(int year, int month, int day){
    String[] data;
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("event.txt"));
 
      // 最終行まで読み込む
      String line = "";
      while ((line = br.readLine()) != null) {
        data = line.split(",");
        if(Integer.parseInt(data[0]) == year && Integer.parseInt(data[1]) == month && Integer.parseInt(data[2]) == day){
          event.add(data[3]);
        }
      }
      br.close();
    } catch (IOException e) {
      // BufferedReaderオブジェクトのクローズ時の例外捕捉
      event.add("読み込み失敗");
    }finally {
      return event;
    }
  }
  boolean add(int year,int month,int day, String event_name){
    BufferedWriter bw;
    try {
      // 追記モード
      bw
        = new BufferedWriter(new FileWriter("event.txt"));
      // 新たなデータ行の追加
      bw.write(year + "," + month + "," + day + "," + event_name);
      bw.newLine();
      bw.close();
      check = true;
    } catch (IOException e) {
      // BufferedWriterオブジェクトのクローズ時の例外捕捉
      check = false;
    }
    return check;
  }
  boolean delete(int year,int month,int day,String event_name){
    String[] data = new String[4];
    BufferedReader br;
    BufferedWriter bw;
    try {
      br = new BufferedReader(new FileReader("event.txt"));
      bw = new BufferedWriter(new FileWriter("event.txt"));
 
      // 最終行まで読み込む
      String line = "";
      while ((line = br.readLine()) != null) {
        data = line.split(",");
        if(Integer.parseInt(data[0]) != year || Integer.parseInt(data[1]) != month || Integer.parseInt(data[2]) != day || !(event_name.contentEquals(data[3]))){
          event.add(line);
        }
      }
      for(int i = 0; i < event.size(); i++){
        bw.write(event.get(i));
        bw.newLine();
      }
      bw.close();
      br.close();
      check = true;
    } catch (IOException e) {
      // BufferedReaderオブジェクトのクローズ時の例外捕捉
      check = false;
    }
    return check;
  }
}
