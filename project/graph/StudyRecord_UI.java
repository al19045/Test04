package com.websarva.wings.android.application1b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudyRecord_UI extends AppCompatActivity{

    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView)findViewById(R.id.readtime);
            BufferedReader in = null;
            try {
                FileInputStream file = context.openFileInput("goaltime.txt");
                in = new BufferedReader(new InputStreamReader(file));
                text.setText(in.readLine());
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        Button setgoalbutton = (Button)findViewById(R.id.setgoalbutton);//リスナーをボタンに登録
        setgoalbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {    //ボタンが押された時の処理
                Intent intent = new Intent(getApplication(), GoalSet.class); //インテンントの作成
                startActivity(intent);  //画面遷移
            }
        });
        //findViewById(R.id.buttonCalender).setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent intent = new Intent(StudyRecord_UI.this, Calender_UI.class);
                //startActivity(intent);
            //}
        //});

        //findViewById(R.id.buttonTodo).setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent intent = new Intent(StudyRecord_UI.this, ToDo_UI.class);
                //startActivity(intent);
            //}
        //});

        //findViewById(R.id.buttonKeisoku).setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent intent = new Intent(StudyRecord_UI.this, MainTimerFrame.class);
                //startActivity(intent);
            //}
        //});

        //findViewById(R.id.buttonKiroku).setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent intent = new Intent(StudyRecord_UI.this, MainActivity.class);
                //startActivity(intent);
            //}
        //});
    }
}