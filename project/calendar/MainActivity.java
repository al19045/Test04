package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.CalendarView;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView cal = findViewById(R.id.Calendar);

        //日付タップ時のイベント（山中
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                String txt;//画面表示するテキスト（setTextで結合すると警告が出るため)

                //情報の取得（学習時間・タスク・イベント）
                month = month + 1;
                calendarMain data = new calendarMain();
                data.get(year,month,day);

                //日付の表示
                TextView date = (TextView)MainActivity.this.findViewById(R.id.dayView);
                txt = year + "年" + month + "月" + day + "日";
                date.setText(txt);

                //学習時間の表示
                TextView study = (TextView)MainActivity.this.findViewById(R.id.studyTime);
                int hour = 0, minute = 0;
                hour = data.studyTime/60;
                minute = data.studyTime%60;
                txt = "・学習時間\n" + hour + "時間" + minute + "分\n";
                study.setText(txt);

                //todoの表示
                TextView todo = (TextView)MainActivity.this.findViewById(R.id.todoList);
                txt = "・todo\n";
                if(data.task == null){ //タスクが登録されていない場合
                    txt = txt + "タスクはありません。\n";
                }else{ //タスクがある場合
                    for(int i = 0; i < data.task.size(); i++){
                        txt = txt + data.task.get(i) + "\n";
                    }
                }
                todo.setText(txt);

                //イベントの表示
                TextView event = (TextView)MainActivity.this.findViewById(R.id.eventView);
                txt = "・イベント\n";
                if(data.event == null){ //イベントが登録されていない場合
                    txt = txt + "イベントはありません。\n";
                }else{ //イベントがある場合
                    for(int i = 0; i < data.event.size(); i++){
                        txt = txt + data.event.get(i) + "\n";
                    }
                }
                event.setText(txt);


            }
        });

    }

}