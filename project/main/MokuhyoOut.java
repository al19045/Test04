package com.example.studyapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MokuhyoOut {

    void saveMokuhyo(String inMokuhyo) {
        new AppContext();
        String filename = "";
        String line;

        Context context = AppContext.getAppContext();
        File file = new File(context.getFilesDir(), filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}