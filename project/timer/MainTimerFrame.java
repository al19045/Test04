/****************************************************
 *** Module Name    :   MainTimerFrame.java
 *** Version        :   V1.0
 *** Designer       :   大谷 真由
 *** Date           :   2021.06.22
 *** Purpose        :   タイマー画面を表示する.
 ***
 ***************************************************/
/*
 *** Revision    :
 *** V1.0        :   大谷 真由, 2021.06.22, 作成
 */
package com.example.studdysupport;

/*********************************************/
/*   import file（ファイルの取り込み）    */
/*********************************************/
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainTimerFrame extends AppCompatActivity {
    int m; // 分
    int s; // 秒

    // ヒントダイアログ
    popup pop = new popup("・タイマーの時間を設定して真下のスタートボタンを押すとカウントダウンタイマーを開始します\n" +
            "・ストップウォッチの真下のスタートボタンを押すとカウントアップタイマーを開始します\n" +
            "・画面下部のツールバーから他の画面に遷移できます");

    // アクティビティ開始時に呼び出されるメソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timer_frame);

        // Intent, Button, NumberPicker, BottomNavigationViewの設定
        Intent measurement = new Intent(MainTimerFrame.this, MainMeasurementFrame.class);
        Button timer = findViewById(R.id.button);
        Button stopwatch = findViewById(R.id.button2);
        NumberPicker min = findViewById(R.id.numberPicker);
        NumberPicker sec = findViewById(R.id.numberPicker2);
        BottomNavigationView menu = findViewById(R.id.bnv);
        menu.getMenu().findItem(R.id.Timer).setChecked(true);



        // NumberPicker(分)の詳細
        min.setMaxValue(999);
        min.setMinValue(0);
        min.setFormatter(new NumberPicker.Formatter() {
            @SuppressLint("DefaultLocale")
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });
        // NumberPicker(秒)の詳細
        sec.setMaxValue(59);
        sec.setMinValue(0);
        sec.setFormatter(new NumberPicker.Formatter() {
            @SuppressLint("DefaultLocale")
            @Override
            public String format(int value) {
                return String.format("%02d", value);
            }
        });

        // タイマーのスタートボタンを押した時の処理
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = min.getValue();
                s = sec.getValue();
                if (m == 0 && s == 0){
                    Snackbar.make(v, "1秒以上設定してください", Snackbar.LENGTH_SHORT).show();
                }else {
                    measurement.putExtra("MODE", "COUNTDOWN");
                    measurement.putExtra("MINUTES", m);
                    measurement.putExtra("SECONDS", s);
                    startActivity(measurement);
                }
            }
        });

        // ストップウォッチのスタートボタンを押したときの処理
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measurement.putExtra("MODE", "STOPWATCH");
                startActivity(measurement);
            }
        });

        // BottomNavigationViewの詳細設定(各画面への遷移)
        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.Main :
                        intent = new Intent(MainTimerFrame.this, MainUI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Timer :
                        return true;
                    case R.id.Study :
                        intent = new Intent(MainTimerFrame.this, StudyRecord_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.Calendar :
                        intent = new Intent(MainTimerFrame.this, Calendar_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.ToDo :
                        intent = new Intent(MainTimerFrame.this, Todo_UI.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                }
                return false;
            }
        });
    }

    // ヒントボタンを押したときの処理
    public void onTapEvent(View view) {
        //ダイアログをだす
        pop.show(getSupportFragmentManager(),"popup");
    }
}