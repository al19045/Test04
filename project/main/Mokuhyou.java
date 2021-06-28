package com.example.studyapp;

import android.util.Log;

public class Mokuhyou {
    //　ファイルから目標を読み込み出力する
    String getMokuhyo() {
        String text;
        MokuhyouOut out = new MokuhyouOut();
       text = out.readFile();
        return text;
    }

    // ファイルに目標を保存する
    void setMokuhyo(String text){
        MokuhyouOut out = new MokuhyouOut();
        out.saveFile(text);
    }
}