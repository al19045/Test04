/****************************************************
 *** Module Name    :   PopUpWindow
 *** Version        :   V1.0
 *** Designer       :   馬場　章
 *** Date           :   2021.07.12
 *** Purpose        :   メイン画面でヒントボタンのダイアログが使えないのでその代用のポップアップ
 ***
 ***************************************************/
/*
 *** Revision    :
 *** V1.0        :   馬場　章　2021.07.12 作成

 */

package com.example.studysupport;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class PopUpWindow extends AppCompatActivity {

    @Override
    //画面の作成
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int hight = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(hight*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }
    //メイン画面に戻る
    public void toziru(View v){
        finish();}
}