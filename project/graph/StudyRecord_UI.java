package com.websarva.wings.android.application1b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class StudyRecord_UI extends AppCompatActivity {
    BarChart chart;
    GoalSyori goalsyori = new GoalSyori();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView goaltext = (TextView) findViewById(R.id.readtime);
        goaltext.setText(goalsyori.getgoal());

        Button hintbutton = (Button) findViewById(R.id.hintbutton);//リスナーをボタンに登録
        hintbutton.setOnClickListener(new View.OnClickListener() { //確認ボタンが押されたときの処理
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new popup();
                //ダイアログをだす
                dialogFragment.show(getSupportFragmentManager(), "popup");
            }
        });

        Button setgoalbutton = (Button) findViewById(R.id.setgoalbutton);//リスナーをボタンに登録
        setgoalbutton.setOnClickListener(new View.OnClickListener() {
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
        Calendar cl = Calendar.getInstance();

        int month = cl.get(cl.MONTH) + 1;
        Integer m = Integer.valueOf(month);
        String mon = m.toString();

        int today = cl.get(cl.DATE);
        Integer td = Integer.valueOf(today);
        String t = td.toString();

        cl.add(Calendar.DATE, -1);
        int day1 = cl.get(cl.DATE);
        Integer da1 = Integer.valueOf(day1);
        String d1 = da1.toString();

        cl.add(Calendar.DATE, -1);
        int day2 = cl.get(cl.DATE);
        Integer da2 = Integer.valueOf(day2);
        String d2 = da2.toString();

        cl.add(Calendar.DATE, -1);
        int day3 = cl.get(cl.DATE);
        Integer da3 = Integer.valueOf(day3);
        String d3 = da3.toString();

        cl.add(Calendar.DATE, -1);
        int day4 = cl.get(cl.DATE);
        Integer da4 = Integer.valueOf(day4);
        String d4 = da4.toString();

        cl.add(Calendar.DATE, -1);
        int day5 = cl.get(cl.DATE);
        Integer da5 = Integer.valueOf(day5);
        String d5 = da5.toString();

        cl.add(Calendar.DATE, -1);
        int day6 = cl.get(cl.DATE);
        Integer da6 = Integer.valueOf(day6);
        String d6 = da6.toString();

        chart = (BarChart) findViewById(R.id.barchart);

        //表示データ取得
        BarData data = new BarData(getBarData());
        chart.setData(data);

        //Y軸(左)
        YAxis left = chart.getAxisLeft();
        left.setAxisMinimum(0);
        left.setLabelCount(5);
        left.setDrawTopYLabelEntry(true);

        //Y軸(右)
        YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);
        right.setDrawZeroLine(true);
        right.setDrawTopYLabelEntry(true);

        //X軸
        XAxis xAxis = chart.getXAxis();
        //X軸に表示するLabelのリスト(最初の""は原点の位置)
        int mon1;
        if (cl.get(cl.MONTH) == 0) {
            mon1 = 12;
        } else {
            mon1 = cl.get(cl.MONTH);
        }
        Integer mon1i = Integer.valueOf(mon1);
        String mon1s = mon1i.toString();

        if (today == 1) {
            final String[] labels = {"", mon1s + "/" + d6, mon1s + "/" + d5, mon1s + "/" + d4, mon1s + "/" + d3, mon1s + "/" + d2, mon1s + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        } else if (today == 2) {
            final String[] labels = {"", mon1s + "/" + d6, mon1s + "/" + d5, mon1s + "/" + d4, mon1s + "/" + d3, mon1s + "/" + d2, mon + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        } else if (today == 3) {
            final String[] labels = {"", mon1s + "/" + d6, mon1s + "/" + d5, mon1s + "/" + d4, mon1s + "/" + d3, mon + "/" + d2, mon + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        } else if (today == 4) {
            final String[] labels = {"", mon1s + "/" + d6, mon1s + "/" + d5, mon1s + "/" + d4, mon + "/" + d3, mon + "/" + d2, mon + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        } else if (today == 5) {
            final String[] labels = {"", mon1s + "/" + d6, mon1s + "/" + d5, mon + "/" + d4, mon + "/" + d3, mon + "/" + d2, mon + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        } else if (today == 6) {
            final String[] labels = {"", mon1s + "/" + d6, mon + "/" + d5, mon + "/" + d4, mon + "/" + d3, mon + "/" + d2, mon + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        } else {
            final String[] labels = {"", mon + "/" + d6, mon + "/" + d5, mon + "/" + d4, mon + "/" + d3, mon + "/" + d2, mon + "/" + d1, mon + "/" + t};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        }
        XAxis bottomAxis = chart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setDrawLabels(true);
        bottomAxis.setDrawGridLines(false);
        bottomAxis.setDrawAxisLine(false);

        //グラフ上の表示
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setClickable(false);
    }
    //棒グラフのデータを取得
    private List<IBarDataSet> getBarData() {//表示させるデータ
        Calendar cl=Calendar.getInstance();
        ArrayList<BarEntry> entries = new ArrayList<>();
        DailyStudyTime dailystudytime=new DailyStudyTime();
        for(int i=7;i>0;i--){
            int time=dailystudytime.read(cl);
            if(time==-1)
                time=0;
            entries.add(new BarEntry(i,time));
            cl.add(Calendar.DATE,-1);
        }

        List<IBarDataSet> bars = new ArrayList<>();
        BarDataSet dataSet = new BarDataSet(entries, "学習時間");

        //Barの色をセット
        bars.add(dataSet);

        return bars;
    }
}






