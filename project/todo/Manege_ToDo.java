package com.example.studdysupport;

//package com.example.gamn4;
//Manage_ToDO リスト管理部　csvファイルに書き込み
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;



//日別時間管理部
//package com.example.studdysupport;


class StudyTime{
    Calendar date;
    int time;
    String day;
    String task;
    String status;
    int id;
}

public class Manege_ToDo {
    static List<StudyTime> studylist = new ArrayList<StudyTime>();
    static String filename = "ToDo.csv";

    TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");

    public String readFile() {
        String line;
        Log.i("before read", "" + studylist);

        new AppContext();
        Context context = AppContext.getAppContext();
        File file = new File(context.getFilesDir(), filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                StudyTime study = new StudyTime();
                Calendar cal = Calendar.getInstance(timezone);
                String[] split = line.split(",");
                cal.clear();
                cal.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]),
                        0, 0, 0);
                cal.set(Calendar.MILLISECOND, 0);

                study.date = cal;

                // study.time = Integer.parseInt(split[3]);
                study.task = split[3];
                study.status = split[4];
                study.id = Integer.parseInt(split[5]);
                // study.task=(split[4]);
                studylist.add(study);

            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
         /*   try {
                FileWriter writer = new FileWriter(file);
                PrintWriter pw = new PrintWriter(new BufferedWriter(writer));
                Calendar today = Calendar.getInstance(timezone);
                today.set(Calendar.HOUR_OF_DAY, 0);
                today.set(Calendar.MINUTE, 0);
                today.set(Calendar.SECOND, 0);
                today.set(Calendar.MILLISECOND, 0);
                StudyTime study = new StudyTime();
                study.date = today;
                study.time = 0;
                study.task = null;
                study.status=null;
                study.id=0;
                studylist.add(study);
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
                df.setTimeZone(timezone);
                pw.println(df.format(study.date.getTime()) + "," + study.time + "," + study.task + "," + study.status + "," + study.id);
                Log.i("write1", df.format(study.date.getTime()) + "," + study.time + "," + study.task + "," + study.status + "," + study.id);
                pw.close();
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }
    */
        }

        return  studylist.get(1).day;
    }


    boolean Write(String time, String task, String aa, int bb) {
        Calendar today = Calendar.getInstance(timezone);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        boolean isSucceeded = false;

        if (studylist.size() == 0) {
            readFile();
        }
        if (studylist.size() == 0) {
            StudyTime study = new StudyTime();
           /* study.date = today;
            study.time = 0;
            studylist.add(study);
        }*/
            study.date = today;
            study.day = time;
            study.task = task;
            study.status = aa;
            study.id = bb;
            studylist.add(study);
        }else {
            int num = studylist.size() - 1;
            System.out.println(studylist.get(num).date);
            System.out.println(today);
            System.out.println(studylist.get(num).date.compareTo(today));
      /*  if (studylist.get(num).date.compareTo(today) == 0) {
            studylist.get(num).time += studytime;
        }else{
            StudyTime todaystudy = new StudyTime();
            todaystudy.date = today;
            todaystudy.time = studytime;
            studylist.add(todaystudy);
        }
*/
            StudyTime todaystudy = new StudyTime();
            todaystudy.date = today;
            todaystudy.day = time;
            todaystudy.task = task;
            todaystudy.status = aa;
            todaystudy.id = bb;
            studylist.add(todaystudy);
        }
        try {
            OutputStream os = AppContext.getAppContext().openFileOutput(filename, Context.MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
            for (int i = 0; i < studylist.size(); i++) {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
                df.setTimeZone(timezone);
                //変更・+studylist.get(i).time);
                pw.println(/*df.format(studylist.get(i).date.getTime()) */  studylist.get(i).day + "," + studylist.get(i).task + "," + studylist.get(i).status + "," +
                        studylist.get(i).id);
                Log.i("write", df.format(studylist.get(i).date.getTime()) + "," + studylist.get(i).task + "," + studylist.get(i).status + "," +
                        studylist.get(i).id);
            }
            pw.close();
            isSucceeded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSucceeded;
    }
/*
    int Read(Calendar day) {
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        int time = -1;
        if (studylist.size() == 0) {
           // readFile();
        }
        for (int i = 0; i < studylist.size(); i++) {
            if (studylist.get(i).date.compareTo(day) == 0) {
                time = studylist.get(i).time;
            }

             }

        return time;
    }
}*/
}