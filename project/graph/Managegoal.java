package com.example.StudySupport;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Managegoal {
    Context context = AppContext.getAppContext();
    String filename = "goaltime.txt";
    File file = new File(context.getFilesDir(), filename);

    public String readFile() {
        String text = null;
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)));
            text = br.readLine();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    public void saveTime(String str) {//入力された値をファイルに保存する
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void resetTime(String str) {//入力された値をファイルに保存する
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}