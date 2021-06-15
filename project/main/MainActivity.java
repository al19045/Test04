package com.example.studyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //別画面遷移
        findViewById(R.id.buttonCalender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calender_UI.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonTodo).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToDo_UI.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonKeisoku).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainTimerFrame.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonKiroku).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyRecord＿UI.class);
                startActivity(intent);
            }
        });

        ((Button)findViewById(R.id.mokuhyo)).setOnClickListener(
            	new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                String[] text = new String[0];
                //テキスト入力を受け付けるビューを作成します。
                final EditText Bigmokuhyo = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("目標を入力してください")
                    //setViewにてビューを設定します。
                    .setView(Bigmokuhyo)
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //テキスト表示設定を入れる


                        }
                    })

                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            //過去の目標を持ってくる

                        }
                    })
                    .show();
            
            }
        });

    }
}

