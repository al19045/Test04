/*********************************************
 ***  Module Name		: calendarUI.java
 ***  Version		: V1.0
 ***  Designer		: 山中 祐人
 ***  Date			: 2021.07.03
 ***  Purpose       	: カレンダー画面を表示する
 ********************************************/
package com.example.studysupport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.studysupport.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class calendarUI extends AppCompatActivity {
    int globalyear,globalmonth,globalday;//カレンダーで選択されている年月日

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView cal = findViewById(R.id.Calendar);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                String txt;//画面表示するテキスト内容
                globalyear = year;
                globalmonth = month + 1;
                globalday = day;
                //情報の取得（学習時間・タスク・イベント）
                calendarMain data = new calendarMain();
                //boolean check = data.add(2021, 6, 15, "test");
                data.get(globalyear, globalmonth, globalday);

                //日付の表示
                TextView date = (TextView) calendarUI.this.findViewById(R.id.dayView);
                txt = globalyear + "年" + globalmonth + "月" + globalday + "日";
                date.setText(txt);

                //学習時間の表示
                TextView study = (TextView) calendarUI.this.findViewById(R.id.studyTime);
                int hour = 0, minute = 0;//学習時間(hour時間minute分)
                hour = data.studyTime / 60;
                minute = data.studyTime % 60;
                txt = "・学習時間\n" + hour + "時間" + minute + "分\n";
                study.setText(txt);

                //todoの表示
                TextView todo = (TextView) calendarUI.this.findViewById(R.id.todoList);
                txt = "・todo\n";
                if (data.task == null) { //タスクが登録されていない場合
                    txt = txt + "タスクはありません。\n";
                } else { //タスクがある場合
                    for (int i = 0; i < data.task.size(); i++) {
                        txt = txt + data.task.get(i) + "\n";
                    }
                }
                todo.setText(txt);

                //イベントの表示
                TextView event = (TextView) calendarUI.this.findViewById(R.id.eventView);
                txt = "";
                if (data.event.size() == 0) { //イベントが登録されていない場合
                    txt = txt + "イベントはありません。\n";
                } else { //イベントがある場合
                    for (int i = 0; i < data.event.size(); i++) {
                        txt = txt + data.event.get(i) + "\n";
                    }
                }
                event.setText(txt);
                Log.d("calendar","get data");
            }
        });
    }
    //イベント情報の追加(addButtonクリック時の動作)
    public void addOnClick(View view) {
        final EditText editText = new EditText(this); //入力受付用テキストボックス
        TextView event = (TextView) calendarUI.this.findViewById(R.id.eventView); //イベント情報を出力するテキストボックス
        editText.setHint("イベント名");
        AlertDialog.Builder alertadd = new AlertDialog.Builder(this); //アラートダイアログ呼び出し
        alertadd.setTitle("イベントの追加");
        alertadd.setMessage(globalyear+"-"+globalmonth+"-"+globalday);
        alertadd.setView(editText);
        //完了ボタンクリック時の操作
        alertadd.setPositiveButton("完了",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                calendarMain cal = new calendarMain();
                boolean check = cal.add(globalyear,globalmonth,globalday,editText.getText().toString());
                cal.get(globalyear,globalmonth,globalday);
                String txt = "";
                for (int i = 0; i < cal.event.size(); i++) {
                    txt = txt + cal.event.get(i) + "\n";
                }
                if(!check){
                    txt = "書き込み失敗";
                }
                event.setText(txt);
                Log.d("calendar","add data");
            }
        });
        alertadd.setNegativeButton("キャンセル", null);
        alertadd.show();
    }

    //イベント情報の削除
    public void deleteOnClick(View view) {
        calendarMain cal = new calendarMain();
        cal.get(globalyear,globalmonth,globalday);
        boolean[] flug = new boolean[cal.event.size()];
        String[] eventname = new String[cal.event.size()];
        for(int i = 0; i<cal.event.size(); i++){
            flug[i] = false;
            eventname[i] = cal.event.get(i);
        }
        AlertDialog.Builder alertdelete = new AlertDialog.Builder(this);
        alertdelete.setTitle("イベントの削除");
        alertdelete.setMessage(globalyear+"-"+globalmonth+"-"+globalday);
        alertdelete.setMultiChoiceItems((CharSequence[])eventname, flug,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        flug[which] = isChecked;
                    }
                });
        alertdelete.setPositiveButton("完了",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                TextView event = (TextView) calendarUI.this.findViewById(R.id.eventView);
                for (int i = 0; i < cal.event.size(); i++) {
                    if(!flug[i]){
                        cal.delete(globalyear,globalmonth,globalday,eventname[i]);
                    }
                }
                cal.get(globalyear,globalmonth,globalday);
                String txt = "";
                for (int i = 0; i<cal.event.size(); i++){
                    txt = txt + cal.event.get(i) + "\n";
                }
                if(txt == ""){
                    txt = "イベントはありません";
                }
                event.setText(txt);
            }
        });
        alertdelete.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertdelete.show();
    }
}