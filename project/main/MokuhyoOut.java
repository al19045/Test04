package com.example.studyapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MokuhyoOut {
    Context context = AppContext.getAppContext();
    String fileName = "Bigmokuhyo.txt";
    File file = new File(context.getFilesDir(), fileName);


    String readFile(){
        String text = null;
        try {
            BufferedReader br = new BufferedReader((
                                    new InputStreamReader(
                                            new FileInputStream(file),
                                            "UTF-8")));
            text = br.readLine();
            br.close();

        } catch (IOException e) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("大きな目標を入力してください");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return text;
    }


    void saveFile(String text){
        //ファイルの削除
        file.delete();
        File file2 = new File(context.getFilesDir(),
                                fileName);

        try{
            PrintWriter pw = new PrintWriter(
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                                new FileOutputStream(file2),
                                                "UTF-8")));
            pw.print(text);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}