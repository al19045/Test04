package com.example.Trytodo;
//ToDo_Input　入力画面から入力を受け取り渡す　処理部へ?管理部へ?タスク入力
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.MotionEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ToDo_Input extends AppCompatActivity {
    EditText timers_name_edit_text2;
    EditText timers_name_edit_text;
    InputMethodManager inputMethodManager;
    private LinearLayout mainLayout;
    static int idhuru;
    TimeZone timezone = TimeZone.getTimeZone("Asia/Tokyo");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mainLayout = (LinearLayout) findViewById(R.id.setting_layout);
        //EditText inputTime = findViewById(R.id.timers_name_edit_text2);

        timers_name_edit_text2 = (EditText) findViewById(R.id.timers_name_edit_text2);
        timers_name_edit_text = (EditText) findViewById(R.id.timers_name_edit_text);
    }
 /*   p
 datePickerDialog.show();
    }*/
    public void timers_name_edit_text2_onClick(View View) {

           //Calendarインスタンスを取得
            final Calendar date = Calendar.getInstance();

            //DatePickerDialogインスタンスを取得
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ToDo_Input.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //setした日付を取得して表示

                            timers_name_edit_text2.setText(String.format("%d,%02d,%02d", year, month + 1, dayOfMonth));

                        }
                    },

                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DATE)
            );


            //dialogを表示
            datePickerDialog.show();

        }

    public void task_on(View view) {

    }

    public void hozon_on(View view) {
     //   Intent subIntent = new Intent(getApplicationContext(),MainActivity.class);
        Manege_ToDo dst = new Manege_ToDo();
        java.util.Calendar cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        java.util.Calendar cal2 = java.util.Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        cal.clear();
        cal.set(2021, 5, 15);
        String day = timers_name_edit_text2.getText().toString();
        String str = timers_name_edit_text.getText().toString();
        boolean status1=true;
        String status=String.valueOf(status1);

        //subIntent.putExtra("str",str);
       // setResult(RESULT_OK, subIntent);

        //dst.Write(str,status);
        //id設定
        Date dateObj= new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        df.setTimeZone(timezone);
     String display=df.format(dateObj);
     idhuru=idhuru+1;
    String  c=display+String.format("%02d",idhuru);



     int id=Integer.parseInt(c);
     dst.Write(day,str,status,id);
        //df.setTimeZone(timezone);

        //Log.i("read", "t = " + dst.Read(cal));

        finish();
    }

    public boolean onTouchEvent(MotionEvent event) {

// キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
// 背景にフォーカスを移す
        mainLayout.requestFocus();

        return true;
    }
}
//EditText timers_name_edit_text=findViewById(R.id.timers_name_edit_text);
    // Intent intent=  new Intent();
//intent.putExtra("timers_name_edit_text",timers_name_edit_text.getText().toString());

//setResult(RESULT_OK,intent);
  //  finish();
   // }



//public void timers_name_edit_text2_onClick(View view) {
// });
